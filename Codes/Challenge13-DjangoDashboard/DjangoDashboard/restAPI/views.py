from django.shortcuts import render
from rest_framework import viewsets
from .models import PIData
from .serializers import PISerializer

class PIView(viewsets.ModelViewSet):
    queryset = PIData.objects.all()
    serializer_class = PISerializer
