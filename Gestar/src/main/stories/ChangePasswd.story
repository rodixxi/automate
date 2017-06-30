Cambio de Contrasenia

Narrative:
"1 Se ingresa a la aplicación con un usuario de Prueba
2 Se ingresa a la carpeta Cambiar Contraseña, ubicada en Carpetas del Sistema/ Cambiar contraseña.
3 Se abrira una nueva ventana, se completa los siguientes campos: Contraseña anterior, Nueva contraseña, Confirmar nueva contraseña
4 Se selecciona la opción Aceptar."
					 
Scenario:  cambio de contrasenia
Given Navigate to the http://10.201.4.191/desav2_w/auth/login url in the chrome browser
When me conecto a gestar...
When abrir carpeta de Sistemas...
When abro la carpeta cambiar contra...
Then completar los campos contras...


					 
 