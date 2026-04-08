from django.urls import path
from django.conf.urls.static import static
from django.conf import settings
from . import views


urlpatterns = [
    path('', views.index, name="urlindex"), 
    path('entrar', views.entrar, name="urlentrar"),
    path('sair', views.sair, name="urlsair"),
    path('quiz', views.quiz, name='urlquiz'),
    path('boas-vindas/<str:cracha>/', views.boas_vindas, name='boas_vindas'),
    path('<str:cracha>/desafio/<int:quiz_numero>/', views.quiz_detail, name='quiz_detail'),
    path('<str:cracha>/desafio/<int:quiz_numero>/reset/', views.reset_quiz, name='reset_quiz'),
]


urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)