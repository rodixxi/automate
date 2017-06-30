Sample story

Narrative:
"1 Iniciar sesión en Gestar, ingresando Usuario (Gestar Implementacion) y luego presionar ""Iniciar Sesión"".
2 Hacer click en la carpeta ""Planificador Semanal"".
3 Se selecciona una Fecha y  ""Team =   Team C6"" , se selecciona el botón ""Planificador"".
4 Se asigna la ""actividad1"" a un recurso, se le asigna 40 HS.
5 Se asigna la ""actividad2"" a un recurso, se le asigna 40 HS.
6 Se asigna la ""actividad1"" a un recurso.
7 Se selecciona una Fecha y  ""Team =   Team C6"" , se selecciona el botón ""Planificador"".
8 Se asigna la ""actividad1"" a un recurso, se le asigna 40404040404040 HS
"

					 
Scenario:  A scenario is a collection of executable steps of different type
Given Navigate to the http://giap10/w/ url in the chrome browser
When me conecto a gestar con el usuario: doorsevn la pass evnusr a la instancia GESTAR ITIL
When abrir la carpeta Carpetas públicas
When abrir la carpeta Gestar CRM
When abrir la carpeta Proyectos
When abrir la carpeta Planificador
Then seleccionar lunes de la semana en curso
Then seleccionar equipo Team Alfonso
Then apreto planificar


