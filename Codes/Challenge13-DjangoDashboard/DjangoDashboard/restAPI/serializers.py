from rest_framework import serializers
from .models import PIData

class PISerializer(serializers.ModelSerializer):
    class Meta:
        model = PIData
        fields = ("id", "zaman", "value")