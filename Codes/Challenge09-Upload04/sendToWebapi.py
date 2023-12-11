#sends value "12.6" to webapi - timestamp = fixed "2018-11-23T14:00:00Z"
import requests 
import httplib2 
import urllib3 

# defining the api-endpoint  
#\\lehmannme7480\AAA
API_ENDPOINT = "https://172.16.4.95/piwebapi/streams/F1DP9gA_4i5ui0aJABcUJd1W1gDgAAAAUEk0REVWUElcWU5ZUE9JTlQ/value/"

# data to be sent to api 
data = {
  "Timestamp": "2019-09-25T16:00:00Z",
  "UnitsAbbreviation": "m",
  "Good": True,
  "Questionable": False,
  "Value": 13
} 
 
  
# sending post request and saving response as response object 
# you can generate Basic Authentication header on the website
#   https://www.blitter.se/utils/basic-authentication-header-generator/
headers = {'Content-Type': 'application/json','Authorization': "Basic S01UXFBJVmlzaW9uOkttXzEyMzQ=", "X-Requested-With": "XMLHttpRequest"}
r = requests.post(url = API_ENDPOINT,verify = False, json=data, headers=headers) 
# extracting response text  
pastebin_url = r.text 
print(r)
