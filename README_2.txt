MEMBRES :
Théo SOUCHON
Vincent BRUNET
Corentin GERMAIN
Baptiste ASSELIN


STRUCTURE :
	2 programmes :
	- "Code" : programme principal -> le main : fr.rclens.projetpointeuse.controller.Main
	- "pointeuse" : programme de la pointeuse -> le main : fr.rclens.projetpointeuse.view.ViewTimeClock
Les 2 programmes sont des projets MAVEN, donc le chemin des mains et les librairies qu'ils nécessitent sont présents dans les fichiers "pom.xml"
des 2 projets.


STRUCTURE MVC :
"Code" :: possède la séparation standart d'un mvc (model,view,controller) mais il possède aussi un paquet façade afin de contrôler l'utilisation
du modèle par l'utilisateur. Ce qui signifie que l'utilisateur doit passer par les interfaces de la façade et non par le model directement.
Il possède aussi un paquet stub qui contient une classe unique Stub qui sert à charger les données (mais on préfèrera les données sérialisé dans "test.dat").
De plus, les classes EmployeeTable, TimeCheckTable, TimeSheetTable sont présents dans le controller car elles servent de classes tampons pour stocker les
données dans la vue (TableView javafx). Il y a aussi un dossier test qui nous a servi à faire les tests unitaires de notre modèle à la racine de "src".

"Pointeuse" :: Reprend l'architecture model,view,controler avec le patron de la façade.

NOTES IMPORTANTES :
On ne peut pas pointer lors d'un week-end (à faire attention pour les tests)
Regarder dans la console afin de trouver un UUID (id de l'employé pour éviter de le recopier, voir vidéo github : "Simuler un pointage")
Il faut, pour tester le pointage d'un employé :
Dans l'ordre : lancer la pointeuse -> parametrer le port -> lancer l'appli principal -> parametrer le port -> tester
Il se peut que cela marche dans n'importe quel ordre mais c'est l'ordre que nous faisions pour tester (et donc celui certain qui devrait fonctionner)

GITHUB SOURCE VIDEOS DEMONSTRATION :
https://github.com/TheoSouchon/VideoTutoJavaPointeuse/blob/main/README.md



