checkLicencias

Narrative:
1 Se ingresa a la aplicación con un usuario de perfil Administardor.
2 Ir a  Carpetas Sistema/ Administrador de usuarios.
3 Se ingresa al menu Cuentas,  seleccionar la opción Nuevo Usuario.
4 Abierta la ventana se completa  el campo ""Nombre Completo"" y ""Nombre de Usuario"".
5 Se selecciona la opción Guardar.
6 Se selecciona el contacto y se ingresa al menu cuentas , seleccionar la opción Propiedades.
7 Abierta la ventana,  se modifica el nombre del contacto y se agrega una descricpción.
8 Se presiona el botón Guardar.
9 Se selecciona el contacto, se ingresa al menu cuentas se presiona el botón Eliminar."
					 
Scenario:  Login

Given Navigate to the http://10.201.4.191/desav2_w/auth/login url in the chrome browser
When me conecto a gestar...
When abrir carpeta de Sistemas...
When abrir carpeta licencia...
Then revisar grilla licenca

