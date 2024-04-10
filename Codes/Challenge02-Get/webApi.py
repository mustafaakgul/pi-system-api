import requests
from requests.auth import HTTPBasicAuth

user = '{USER}\PI4DEV'
passw = 'Password1'
url = "https://172.16.4.95/piwebapi/"
pathx = 'C:\\Users\\PI4Dev\\Desktop\\PIDevPython'
#headers = {}
#print(user, passw, url)
response = requests.get(url, verify = False , auth=HTTPBasicAuth(user, passw))
#print(response.headers)
print(response.text)

# url = "https://devdata.osisoft.com/piwebapi/streams/F1DPW6Wlk0_Utku9vWTvxg45oAMRcAAAUElTUlYxXENJVFlCSUtFU18oVE8pQklLRV8wMS4gQ0VSVE9TQSAgIFAuTEUgQVZJU19FTVBUWSBTTE9UUw/value"
# response = requests.get(url, verify=False, auth=HTTPBasicAuth('webapiuser', '!try3.14webapi!'))
# print(response.headers)
# print(response.text)



#{'Transfer-Encoding': 'chunked', 'Content-Type': 'application/json; charset=utf-8', 'Server': 'Microsoft-HTTPAPI/2.0', 'Date': 'Wed, 11 Sep 2019 14:31:53 GMT'}


