import requests
from requests.auth import HTTPBasicAuth
import json

url = "https://{IP}/piwebapi/dataservers/F1DS9gA_4i5ui0aJABcUJd1W1gUEk0REVWUEk/points/"
serverWebID = "F1DS9gA_4i5ui0aJABcUJd1W1gUEk0REVWUEk"
#response = requests.get(url,auth=HTTPBasicAuth('PI4DEV','Password1'),verify=False)
myData = {
"Name": "PointNameasda",
"PointClass": "classic",
"PointType": "Float32",
"Step": False
}
addData = requests.post(url,data=json.dumps(myData),auth=HTTPBasicAuth('PI4DEV','Password1'),verify=False)
print(addData)