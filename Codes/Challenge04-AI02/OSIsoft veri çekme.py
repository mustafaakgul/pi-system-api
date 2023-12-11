# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

import sys 
import clr
clr.AddReference(r"C:\Program Files (x86)\PIPC\AF\PublicAssemblies\4.0\OSIsoft.AFSDK")

from OSIsoft.AF import *
from OSIsoft.AF.PI import *
from OSIsoft.AF.Asset import *
from OSIsoft.AF.Data import *
from OSIsoft.AF.Time import *
from OSIsoft.AF.UnitsOfMeasure import *



piServers = PIServers()
piServer = piServers.DefaultPIServer

pt = PIPoint.FindPIPoint(piServer , "sinusoid")
name = pt.Name.lower()
print('\nShowing PI Tag CurrentValues from {0}'.format(name))
current_value = pt.CurrentValue()
print('{0}\s Current Value: {1}'.format(name, current_value.Value))

timerange = AFTimeRange("*-12h","*")
recorded = pt.RecordedValues(timerange, AFBoundaryType.Inside,"",False)
print('\nShowing PI Tag RecordedValues from{0}'. format(name))
for event in recorded:
    print('{0} value: {1}'.format(event.Timestamp.LocalTime,event.Value))
 
plotvalues = pt.PlotValues(timerange, 15)
print('\nShowing PI Tag PlotValues from {0}'.format(name))
for event in plotvalues:
    print('{0} value {1}'.format(event.Timestamp.LocalTime, event.Value))
 
span = AFTimeSpan.Parse("10m")

interpolated = pt.InterpolatedValues(timerange, span, "",False)
print('\nShowing PI Tag InterpolatedValues from{0}'.format(name))
for event in interpolated:
    print('{0} value {1}'.format(event.Timestamp.LocalTime, event.Value))

summaries = pt.Summaries(timerange, span, AFSummaryTypes.Average, AFCalculationBasis.TimeWeighted, AFTimestampCalculation.Auto)
print('\nShowing PI Tag SummariesValues(Average) from {0}'.format(name))
for summary in summaries:
    for event in summary.Value:
        print('{0} value: {1}'.format(event.Timestamp.LocalTime, event.Value))

#PI Data Archive'de yeni Pointer oluşturma
afServers = PISystems()
afServer = afServers.DefaultPISystem
DB = afServer.Databases.DefaultDatabase

print ('\nCreate Element with Attribute')
if DB.Elements.get_Item("Test New Element") is not None:
    print("Already Existing element: Test New Element")
else:
    newelement = DB.Elements.Add("Test New Element")
    newelement.Description = "Created element from PIthon"
    newattribute = newelement.Attributes.Add("Test Attribute")
    newattribute.DataReferencePlugIn = afServer.DataReferencePlugIns.get_Item("PI Point")
    newattribute.DataReference.ConfigString = "cdt158"
    DB.CheckIn()
    print("Created new element : Test New Element")
