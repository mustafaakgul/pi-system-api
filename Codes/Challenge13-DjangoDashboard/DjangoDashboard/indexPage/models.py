from django.db import models
from django import forms

# Create your models here.

class ApiDetails(models.Model):
    ipAdress = models.CharField(verbose_name="IP Adresi", max_length=15)
    username = models.CharField(verbose_name="Kullanıcı Adı", max_length=30)
    password = models.CharField(max_length=50)

def __str__(self):
    return self.username