import requests
from requests.auth import HTTPBasicAuth
import json
import dash
from dash.dependencies import Input, Output
import dash_core_components as dcc
import dash_html_components as html
import datetime

print("Hello World")

username = r"{USER}\PI4DEV"
password = "Password1"
headers = {"X-Requested-With": "XMLHttpRequest"}
app = dash.Dash()
app.title = "PiSystem"
app.layout = html.Div(children= [
    html.Label("Ne Zaman Başlasın?:  "),
    dcc.Input(id="inputDay", value= "", type="text"),
    html.Label("   Ne Aralıkla?:  "),
    dcc.Input(id="inputTimeInterval", value= "", type="text"),
    html.Hr(),
    html.Div(id="output-graph")
    ])

@app.callback(
    Output(component_id="output-graph", component_property="children"),
    [Input(component_id="inputDay", component_property="value"), Input(component_id="inputTimeInterval", component_property="value")]
)
def update_graph(inputDayData, inputTimeData):
    if(inputDayData == "" or inputTimeData == ""):
        pass
    else:
        url = "https://172.16.4.95/piwebapi/streams/F1DP9gA_4i5ui0aJABcUJd1W1gAQAAAAUEk0REVWUElcU0lOVVNPSUQ/interpolated?startTime=T-{}&endTime={}&Interval={}".format(inputDayData,"T",inputTimeData)
        values = []
        timestamps = []
        response = requests.get(url, verify = False, auth = HTTPBasicAuth(username, password), headers = headers)
        if response.status_code == 200:
            jsonData = json.loads(response.text)["Items"]
            jsonTextLenght = len(jsonData)
            print(jsonData)
            type(jsonData)
            for i in range(0, jsonTextLenght):
                for key, value in jsonData[i].items():
                    if(key == "Value" and value != {'Name': 'Shutdown', 'Value': 254, 'IsSystem': True}):
                        values.append(value)
                        print(values[i])
                    if(key == "Timestamp" and value != {'Name': 'Shutdown', 'Value': 254, 'IsSystem': True}):
                        timestamps.append(value)
                        print(timestamps[i])
            
            print(values)
            print("123")
            

            return dcc.Graph(
                id="example-graph",
                figure={
                    "data":[
                        {'x': timestamps, "y": values, "type": "line", "name": "PiServer Tag"}
                    ],
                    "layout":{
                        "title":"PiServer Tag"
                    }
                }
            )
        else:
            pass



if __name__ == "__main__":
    app.run_server(debug=True)