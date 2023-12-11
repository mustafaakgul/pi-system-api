from django.shortcuts import render, HttpResponse
import requests as req
import json
from requests.auth import HTTPBasicAuth
from restAPI.models import PIData
import dateutil.parser
import datetime
from indexPage.models import ApiDetails

historicalValueResponse = []
recordedValueResponse = []
tag = ""
ipAdress = ""
username = ""
password = ""

def dashboard(request):
    piPoints = getPoints()
    context = {
        "points": piPoints
    }
    if request.method == "POST":
        tagIndex = request.POST["piPoint2"]
        global tag
        tag = piPoints[int(tagIndex) - 1]
        startTime = request.POST["startTime"]
        endTime = request.POST["endTime"]
        interval = request.POST["interval"]
        responseWebId = getTagWebID(tag)
        global historicalValueResponse
        historicalValueResponse = getHistoricalTagValues(responseWebId, startTime, endTime, interval)
        zippedLists = zip(historicalValueResponse[2], historicalValueResponse[0])
        #print(historicalValueResponse[0])
        context = {
            "data":historicalValueResponse[1],
            "timestamp":historicalValueResponse[0],
            "realTime": historicalValueResponse[2],
            "zippedLists":zippedLists,
            "tagName":tag
        }
        return render(request,"table.html", context= context)
    return render(request,"index.html", context= context)

def getUserInfo():
    ipAdressQuery = ApiDetails.objects.all().values_list("ipAdress", flat = True)
    global ipAdress
    ipAdress = ipAdressQuery[0]
    usenameQuery = ApiDetails.objects.all().values_list("username", flat = True)
    global username
    username = usenameQuery[0]
    passwordQuery = ApiDetails.objects.all().values_list("password", flat = True)
    global password
    password = passwordQuery[0]

def getPoints():
    getUserInfo()
    tagNameArray = []
    urlDatabase = "https://{}/piwebapi/dataservers/F1DS9gA_4i5ui0aJABcUJd1W1gUEk0REVWUEk/points".format(ipAdress)
    responseAPI = req.get(url = urlDatabase, auth = HTTPBasicAuth(username= username, password= password), verify=False)
    responseText = json.loads(responseAPI.text)["Items"]
    for i in range(0,len(responseText)):
        for key, value in responseText[i].items():
            if(key == "Name"):
                tagNameArray.append(value)
    return tagNameArray

def getTagWebID(tag):
    getUserInfo()
    urlDatabase = "https://{}/piwebapi/dataservers/F1DS9gA_4i5ui0aJABcUJd1W1gUEk0REVWUEk/points".format(ipAdress)
    responseAPI = req.get(url = urlDatabase, auth = HTTPBasicAuth(username= username, password= password), verify=False)
    responseText = json.loads(responseAPI.text)["Items"]
    for i in range(0,len(responseText)):
        for key, value in responseText[i].items():
            if (key == "Name" and value == tag):
                webId = responseText[i]["WebId"]
    return webId

def getCurrentTagValue(webId):
    getUserInfo()
    valueURL = "https://{}/piwebapi/streams/{}/value".format(ipAdress, webId)
    responseForValue = req.get(url = valueURL, auth = HTTPBasicAuth(username= username, password= password), verify=False)
    responseForValueText = json.loads(responseForValue.text)["Value"]
    return responseForValueText

def getHistoricalTagValues(webId, startTime, endTime, interval):
    getUserInfo()
    historicalValuesinFunch = []
    historicalTimeStampsinFunch = []
    historicalRealTimeStampinFunch = []
    keyValue = 0
    keyZaman = ""
    boolkeyValue = False
    boolkeyZaman = False
    url = "https://{}/piwebapi/streams/{}/interpolated?startTime=T-{}&endTime={}&Interval={}".format(ipAdress, webId, startTime, endTime, interval)
    responseHistoricalTagValue = req.get(url = url, auth = HTTPBasicAuth(username= username, password= password), verify=False)
    responseHistoricalTagValueText = json.loads(responseHistoricalTagValue.text)["Items"]
    PIData.objects.all().delete()
    for i in range(0, len(responseHistoricalTagValueText)):
        for key, value in responseHistoricalTagValueText[i].items():
            if(key == "Value"):
                if (str(value).startswith("{") == False):
                    historicalValuesinFunch.append(value)
                    keyValue = value
                    boolkeyValue = True
                else:
                    historicalValuesinFunch.append(-9999)
                    boolkeyValue = True
                    keyValue = -9999
            if(key == "Timestamp"):
                timeDelta = datetime.timedelta(hours=3)
                newDateTestString = str(value).replace('Z', '')
                newDateTest = dateutil.parser.parse(newDateTestString)
                dateTimeVar = datetime.datetime.strptime(str(newDateTest), r'%Y-%m-%d %H:%M:%S')
                lastDateTime = dateTimeVar + timeDelta
                historicalTimeStampsinFunch.append(value)
                historicalRealTimeStampinFunch.append(str(lastDateTime))
                keyZaman = value
                boolkeyZaman = True
            if (boolkeyValue == True and boolkeyZaman == True):
                modelValue = PIData(value = keyValue, zaman = keyZaman)
                modelValue.save()
                boolkeyValue = False
                boolkeyZaman = False
    #print(historicalTimeStampsinFunch)
    return historicalValuesinFunch, historicalTimeStampsinFunch, historicalRealTimeStampinFunch

def recorded(request):
    piPoints = getPoints()
    context = {
        "points": piPoints
    }
    #print("Points: ", piPoints)
    if request.method == "POST":
        tagIndex = request.POST["piPoint"]
        global tag
        tag = piPoints[int(tagIndex) - 1]
        #print("Tag: ", tag)
        startTime = request.POST["startTime"]
        endTime = request.POST["endTime"]
        responseWebId = getTagWebID(tag)
        #print(responseWebId)
        global recordedValueResponse
        recordedValueResponse = getRecordedTagValues(responseWebId, startTime, endTime)
        recordedZippedLists = zip(recordedValueResponse[2], recordedValueResponse[0])
        context = {
            "data":recordedValueResponse[1],
            "timestamp":recordedValueResponse[0],
            "realTime": recordedValueResponse[2],
            "zippedLists":recordedZippedLists,
            "tagName":tag
        }
        return render(request, "table.html", context=context)
    return render(request, "recordedData.html", context=context)

def getRecordedTagValues(webId, startTime, endTime):
    getUserInfo()
    recordedValuesinFunch = []
    recordedTimeStampsinFunch = []
    recordedRealTimeStampinFunch = []
    keyValue = 0
    keyZaman = ""
    boolkeyValue = False
    boolkeyZaman = False
    timeFormat = ""
    url = "https://{}/piwebapi/streams/{}/recorded?startTime=T-{}&endTime={}".format(ipAdress, webId, startTime, endTime)
    responseRecordedTagValue = req.get(url = url, auth = HTTPBasicAuth(username= username, password= password), verify=False)
    responseRecordedTagValueText = json.loads(responseRecordedTagValue.text)["Items"]
    PIData.objects.all().delete()
    for i in range(0, len(responseRecordedTagValueText)):
        for key, value in responseRecordedTagValueText[i].items():
            if(key == "Value"):
                if (str(value).startswith("{") == False):
                    recordedValuesinFunch.append(value)
                    keyValue = value
                    boolkeyValue = True
                else:
                    recordedValuesinFunch.append(-9999)
                    boolkeyValue = True
                    keyValue = -9999
            if(key == "Timestamp"):
                timeDelta = datetime.timedelta(hours=3)
                newDateTestString = str(value).replace('Z', '')
                newDateTest = dateutil.parser.parse(newDateTestString)
                if (str(value)[19] == '.'):
                    timeFormat = r'%Y-%m-%d %H:%M:%S.%f'
                else:
                    timeFormat = r'%Y-%m-%d %H:%M:%S'
                dateTimeVar = datetime.datetime.strptime(str(newDateTest), timeFormat)
                lastDateTime = dateTimeVar + timeDelta
                recordedTimeStampsinFunch.append(value)
                recordedRealTimeStampinFunch.append(str(lastDateTime))
                keyZaman = value
                boolkeyZaman = True
            if (boolkeyValue == True and boolkeyZaman == True):
                modelValue = PIData(value = keyValue, zaman = keyZaman)
                modelValue.save()
                boolkeyValue = False
                boolkeyZaman = False
    return recordedValuesinFunch, recordedTimeStampsinFunch, recordedRealTimeStampinFunch

def chart(request):
    context = {
        "tag":tag
    }
    return render(request, "amchart6.html", context=context)
