FAILLE
Un utilisateur peut changer le progr�s des t�ches d�autres utilisateurs.
EXPLOIT
Cette exploitation permet de changer le pourcentage des taches de tous les utilisateurs sur la plateforme. Ceci peut causer une mauvaise confiance envers la compagnie de la part des utilisateurs et ainsi nuire a la r�putation de la compagnie.

Avant de commencer, il faut qu�il y ait un compte qui va subir l�attaque ayant au minimum une t�che de cr�er. Pour faire l�exploitation, le pirate devra se cr�er un compte normalement puis il devra envoyer une requ�te en utilisant Postman avec cette adresse �localhost:8080/api/progress/ {taskID}/{value}� tout en changeant �{taskID}� et �{value}� a ce qu�il d�sire. Si le pirate ne conna�t pas l�ID de la tache, il peut tout simplement �brutfoce� pour �ventuellement avoir celle qu�il veut. Sur un serveur avec plusieurs personnes, le pirate serait capable de toucher tous les utilisateurs. Postman est utile pour savoir l�ID de la victime directement pour acc�l�rer le test de l�exploitation.

�tapes d�taill�es
1� Aller � la page �r�er un compte�.
2� Cr�er un compte ayant un nom d�utilisateur �victime� et un mot de passe �Protegercontretout�
3� Une fois rediriger a la page accueil, naviguer a la page ajout de tache.
4� Ajouter une tache nomm�e �gros boulot� avec une date du lendemain (la date n�est pas importante)
5� Apr�s avoir cr�� l�activit�, aller sur Postman et faire une requ�te get avec cette URL : localhost:8080/api/home
6� Noter l�ID qui est redonn� de la tache cr��e.
7� Aller sur Postman et cr�er un nouveau compte avec un post a l�adresse localhost:8080/api/id/signup et un combin�-slip raw json : {
�username� :" hack4Life�,     �password� :" hackytacky� }
8� Toujours sur Postman, faire une requ�te get sur �localhost:8080/api/progress/ {taskID}/50" en changeant �{taskID}� par l�ide pr�c�demment not� de la tache.
9� Le changement a �t� fait. Le progr�s du premier utilisateur (victime) a �t� chang�. Pour voir la modification rafraichir la page de l�utilisateur victime.
CORRECTIF

Il faut ajouter un identifiant a la cr�ation de chaque tache d�crivant le propri�taire de caque tache et ensuite faire une comparaison avec un if pour v�rifier si c�est bien le cr�ateur de la tache qui veut faire la modification. la comparaison ressemblerait un peu au code qui suit (se serais dans le fichier ServiceTaskImpl vers la ligne 77/78)

if (repo.findById(taskID).get().createur != repoUser)         {throw new IllegalAccessException();}
