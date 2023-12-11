import clr
clr.AddReference(r"C:\Program Files (x86)\PIPC\AF\PublicAssemblies\4.0\OSIsoft.AFSDK")
import pandas as pd

import matplotlib.pyplot as plt 
import time
from OSIsoft import AF
from OSIsoft.AF import *
import datetime as datetime
import statsmodels.api as sm

#DATABASE BAĞLANTISI
piDB = AF.PI.PIServers().DefaultPIServer
piPoint = AF.PI.PIPoint.FindPIPoint(piDB,"BA:CONC.1")
i=0
future_data = []
starttime = time.time()
#while loop başlangıcı...

while 1:
    #EĞİTİM VERİLERİ ZAMAN DİLİMİ
    startTime = AF.Time.AFTime("*-3d")
    endTime = AF.Time.AFTime("*")
    timeRange = AF.Time.AFTimeRange(startTime, endTime)
    #VERİLERİN ARALIĞI
    span = AF.Time.AFTimeSpan.Parse("15m")
    boundaryType = AF.Data.AFBoundaryType.Inside
    #TAHMİN VERİLERİ
    startPredictTime = AF.Time.AFTime("*")
    sp = datetime.datetime.strptime(startPredictTime.LocalTime.ToString(), '%m/%d/%Y %I:%M:%S %p')
    endPredictTime = AF.Time.AFTime("*+2d")
    ep = datetime.datetime.strptime(endPredictTime.LocalTime.ToString(),'%m/%d/%Y %I:%M:%S %p')
    
    #EĞİTİM VERİLERİNİN ÇEKİLMESİ
    recordedValues = piPoint.InterpolatedValues(timeRange,span, "",False)

    recordedValuesDict = dict()
    #EĞİTİM VERİLERİNİN DİCTİONARY'e ATILMASI
    for event in recordedValues:
        dt = datetime.datetime.strptime(event.Timestamp.LocalTime.ToString(),'%m/%d/%Y %I:%M:%S %p')
        recordedValuesDict[dt] = event.Value
    
    #EĞİTİM VERİLERİNDEN OLUŞAN DATAFRAME'in OLUŞTURULMASI
    df = pd.DataFrame(recordedValuesDict.items(),columns=["TimeStamp","Value"])
    df['TimeStamp'] = pd.to_datetime(df['TimeStamp'])
    indexed_df = df.set_index(['TimeStamp']).sort_index()
   #EĞİTİM VERİLERİNİN GRAFİK GÖSTERİMİ
    plt.plot(indexed_df, color = 'blue')

    #REGRESYON
    ar_model = sm.tsa.AR(indexed_df)
    pandas_ar_res = ar_model.fit(maxlag=200,method='cmle', disp=True)
    #TAHMİN
    pred = pandas_ar_res.predict(start=str(sp) , end=str(ep))
    #TAHMİN VERİLERİNİN GRAFİK GÖSTERİMİ
    plt.ion()
    plt.show()
    plt.plot(pred, color='orange')
    plt.draw()
    time.sleep(0.1)
    plt.pause(0.0001)
 
    plt.gcf().clear()

        
