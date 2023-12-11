from django.db import models

class PIData(models.Model):
    zaman = models.CharField(max_length=50, default="", verbose_name="Tarih")
    value = models.FloatField(verbose_name="Değer")
    def __str__(self):
        return self.zaman

