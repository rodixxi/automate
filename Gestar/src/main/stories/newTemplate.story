Narrative:

Scenario: probando template 7.5

Given Navigate to the http://cloudycrm.net/w/auth/Login url in the chrome browser
And me conecto a gestar con el usuario: demo; con la pass: demo; a la instancia template.
And switch to http://cloudycrm.net/w/home/normal; url.

And abrir la carpeta: Automate.
And abrir la carpeta: Automate - Demo.
When crear arhivo nuevo.

!-- When seleccionar tabPanel: tab1.
!--And en el textBox: inLineNormalTextBox; ingreso lala.
!-- And en el campo requerido: inLineRequiredTextBox; ingreso abc.
!--And en el textBox numerico: onLineNumericTextBox; ingreso 123.
!--And en el textArea: multipleLineTextBox; ingreso jojojujaju asdasd asdasdas.
!--And en el textBox password: passTexBox; ingreso lapolola.


!-- And seleccionar tabPanel: tab2.
!--And en el campo andjuntoNuevo: attachments; adjuntar el archivo: C:\lala.txt -
!--And en el campo andjuntoNuevo: attachments; adjuntar el archivo: C:\enigma.txt -
!--And en el campo andjuntoNuevo: attachments; adjuntar el archivo: C:\Users\rcrespillo\Pictures\wallpapers\lake.jpg -
!--And en el campo andjuntoNuevo: attachments; adjuntar el archivo: C:\Users\rcrespillo\Downloads\Estado_Kanavs_V4 (Rodrigo Crespillo).xlsx -
!--And en el campo andjuntoNuevo: attachments; adjuntar el archivo: C:\Users\rcrespillo\Downloads\vista1.xml -
And en el campo andjuntoNuevo: attachments; adjuntar el archivo: C:\Users\rcrespillo\Downloads\CDP_CV2017_3.pdf -
And en el DTPickerNewOnlyTime: DTPcikerOnlyTime; con 00:00.
And en el DTPickerNewOnlyTime: DTPcikerOnlyTime; con 23:59.
And en el DTPickerNewOnlyDate: DTPcikerOnlyDate; cargo por calendario la fecha: 07/07/2017.
And en el DTPickerNewOnlyDate: DTPcikerOnlyDate; cargo por calendario la fecha: 27/07/2017.
And en el DTPickerNewOnlyDate: DTPcikerOnlyDate; cargo por calendario la fecha: 01/07/2017.
And en el DTPickerNewOnlyDate: DTPcikerOnlyDate; cargo por calendario la fecha: 31/12/2017.
And en el DTPickerNewOnlyDate: DTPcikerOnlyDate; cargo por calendario la fecha: 01/01/2018.
And en el DTPickerNewOnlyDate: DTPcikerOnlyDate; cargo por calendario la fecha: 01/01/2017.
And en el DTPickerNew: DTPciker; cargo por calendario la fecha: 07/07/2017, con 00:01.
And en el DTPickerNew: DTPciker; cargo por calendario la fecha: 27/07/2017, con 23:59.
