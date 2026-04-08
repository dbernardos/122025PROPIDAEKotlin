from django import forms
from django.forms import fields
from django.contrib.auth.models import User
from django.contrib.auth.forms import UserCreationForm
from .models import Participante, RespostaQuiz


class UsuarioForm(UserCreationForm):
    class Meta:
        model = User
        fields = ['username', 'email', 'password1', 'password2']

        widgets = {
            'username': forms.TextInput(attrs={
                'class': 'form-control',   
            }),

            'email': forms.TextInput(attrs={
                'class': 'form-control',   
            }),
        }

class ParticipanteForm(forms.ModelForm):
    """Form para registro/login do participante pelo crachá"""
    class Meta:
        model = Participante
        fields = ['cracha', 'nome', 'email']
        widgets = {
            'cracha': forms.TextInput(attrs={
                'class': 'form-control form-control-lg',
                'placeholder': 'Digite ou passe o crachá',
                'autofocus': True
            }),
            'nome': forms.TextInput(attrs={
                'class': 'form-control',
                'placeholder': 'Nome completo (opcional)'
            }),
            'email': forms.EmailInput(attrs={
                'class': 'form-control',
                'placeholder': 'E-mail (opcional)'
            })
        }

class RespostaQuizForm(forms.ModelForm):
    """Form para resposta do quiz"""
    class Meta:
        model = RespostaQuiz
        fields = ['valor_resposta']
        widgets = {
            'valor_resposta': forms.NumberInput(attrs={
                'class': 'form-control form-control-lg',
                'step': '0.01',
                'placeholder': 'Digite sua resposta'
            })
        }