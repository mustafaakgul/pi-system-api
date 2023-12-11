from django.shortcuts import render, redirect
from .models import ApiDetails

# Create your views here.

def index(request):
    if ("interpolied" in request.POST):
        ApiDetails.objects.all().delete()
        ipAdress = request.POST["ipAdress"]
        username = request.POST["username"]
        password = request.POST["password"]
        if (ipAdress != "" and username != "" and password != ""):
            context = {
                "info":"Başarılı"
            }
            apiDetails = ApiDetails(ipAdress = ipAdress, username = username, password = password)
            apiDetails.save()
            return redirect("/dashboard")
        else:
            context = {
                "info":"Başarısız"
            }
            return render(request, "anasayfa.html", context=context)
        
    if ("recorded" in request.POST):
        ApiDetails.objects.all().delete()
        ipAdress = request.POST["ipAdress"]
        username = request.POST["username"]
        password = request.POST["password"]
        if (ipAdress != "" and username != "" and password != ""):
            context = {
                "info":"Başarılı"
            }
            apiDetails = ApiDetails(ipAdress = ipAdress, username = username, password = password)
            apiDetails.save()
            return redirect("/dashboard/recorded")
        else:
            context = {
                "info":"Başarısız"
            }
            return render(request, "anasayfa.html", context=context)
    return render(request, "anasayfa.html")