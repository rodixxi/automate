Narrative:

Scenario: probando template 7.5

Given Navigate to the http://cloudycrm.net/w/auth/Login url in the chrome browser
And me conecto a gestar con el usuario: demo; con la pass: demo; a la instancia template.
And switch to http://cloudycrm.net/w/home/normal; url.

And abrir la carpeta: Automate.
And abrir la carpeta: Automate - Demo.
When crear arhivo nuevo.

!-- When seleccionar tabPanel: tab1.
And en el textBox: inLineNormalTextBox; ingreso lala.
!-- And en el campo requerido: inLineRequiredTextBox; ingreso abc.
And en el textBox numerico: onLineNumericTextBox; ingreso 123.
And en el textArea: multipleLineTextBox; ingreso jojojujaju asdasd asdasdas.
And en el textBox password: passTexBox; ingreso lapolola.


!-- And seleccionar tabPanel: tab2.
And en el campo andjunto: attachments; adjuntar el archivo: C:\lala.txt -

