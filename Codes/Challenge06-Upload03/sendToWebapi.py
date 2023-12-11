#sends value "12.6" to webapi - timestamp = fixed "2018-11-23T14:00:00Z"
import requests 
import httplib2 
import urllib3 

# defining the api-endpoint  
#\\lehmannme7480\AAA
API_ENDPOINT = "https://lehmannme7480/piwebapi//streams/F1DPWFEtPC9kfkW8iqoHc9K9ZAK2oAAATEVITUFOTk1FNzQ4MFxBQUE/value/"

# data to be sent to api 
data = {
  "Timestamp": "2018-11-23T14:00:00Z",
  "UnitsAbbreviation": "m",
  "Good": True,
  "Questionable": False,
  "Value": 12.6
} 
 
  
# sending post request and saving response as response object 
# you can generate Basic Authentication header on the website
#   https://www.blitter.se/utils/basic-authentication-header-generator/
headers = {'Content-Type': 'application/json','Authorization': "Basic b3NpXG1hcnRpbmxlaDpNbGUxOTU5JDM0Nw=="}
r = requests.post(url = API_ENDPOINT,verify = False, json=data, headers=headers) 
# extracting response text  
pastebin_url = r.text 
print(r)
