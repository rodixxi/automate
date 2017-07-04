Sample story

Narrative:
1 Se ingresa a la aplicación con un usuario de perfil Administrador
2 Se ingresa a la carpeta Configuraciones, desde la Carpeta del Sistema/ Configuraciones.
3 Abierta la ventana, se selecciona dos veces, la opción derecho del mouse la sección Doors.ini.
4  Seleccionar Settings Administrator, Hacer doble click en el parámetro a modificar .  Seleccionar  guardar (Volver el valor anterior).
5 Seleccionar Instances.Hacer doble click en el parámetro a modificar.Seleccionar Guardar. (Volver el valor anterior).
6  Seleccionar Servidores LDAP

					 
Scenario:  A scenario is a collection of executable steps of different type
Given Navigate to the http://10.201.4.191/desav2_w/auth/login url in the chrome browser
When me conecto a gestar...
When abrir carpeta de Sistemas...
When abro la carpeta configuraciones...
Then abro el elemento doors.ini
Then vuelvo a la pantalla anterior
Then abro el elemento Settings.Administrator
!-- pierdo el foco. No puedo hacer el cambio
Then selecciono parametro ATTACHMENTS_PATH, modificar con c:\Temp2 y guardar
!-- Then selecciono parametro ATTACHMENTS_PATH, modificar con c:\Temp y guardar

