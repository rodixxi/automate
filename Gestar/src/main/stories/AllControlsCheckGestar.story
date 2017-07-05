Narrative:
Ingreso a Gestar, instancia DESA como usuario ADMIN
Entro a la carpeta "autoMate"
Entro al formulario "autoMate - prueba"
Selecciono el tab2
Selecciono el tab1
Cargo el campo textbox1 con texto plano

Scenario: entro a automate y pruebo los tabs y el control de texto
Given Navigate to the http://10.201.4.191/w/ url in the chrome browser
When me conecto a gestar con el usuario: admin sin pass a la instancia DESA
When abrir la carpeta autoMate
When abrir la carpeta autoMATE - prueba
When crear arhivo nuevo
When seleccionar tabPanel tab2