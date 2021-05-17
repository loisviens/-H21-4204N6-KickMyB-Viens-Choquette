FAILLE
Un utilisateur peut changer le progrès des tâches d’autres utilisateurs.
EXPLOIT
Cette exploitation permet de changer le pourcentage des taches de tous les utilisateurs sur la plateforme. Ceci peut causer une mauvaise confiance envers la compagnie de la part des utilisateurs et ainsi nuire a la réputation de la compagnie.

Avant de commencer, il faut qu’il y ait un compte qui va subir l’attaque ayant au minimum une tâche de créer. Pour faire l’exploitation, le pirate devra se créer un compte normalement puis il devra envoyer une requête en utilisant Postman avec cette adresse «localhost:8080/api/progress/ {taskID}/{value}» tout en changeant «{taskID}» et «{value}» a ce qu’il désire. Si le pirate ne connaît pas l’ID de la tache, il peut tout simplement «brutfoce» pour éventuellement avoir celle qu’il veut. Sur un serveur avec plusieurs personnes, le pirate serait capable de toucher tous les utilisateurs. Postman est utile pour savoir l’ID de la victime directement pour accélérer le test de l’exploitation.

étapes détaillées
1– Aller à la page «réer un compte».
2– Créer un compte ayant un nom d’utilisateur «victime» et un mot de passe «Protegercontretout»
3– Une fois rediriger a la page accueil, naviguer a la page ajout de tache.
4– Ajouter une tache nommée «gros boulot» avec une date du lendemain (la date n’est pas importante)
5– Après avoir créé l’activité, aller sur Postman et faire une requête get avec cette URL : localhost:8080/api/home
6– Noter l’ID qui est redonné de la tache créée.
7– Aller sur Postman et créer un nouveau compte avec un post a l’adresse localhost:8080/api/id/signup et un combiné-slip raw json : {
«username» :" hack4Life»,     «password» :" hackytacky» }
8– Toujours sur Postman, faire une requête get sur «localhost:8080/api/progress/ {taskID}/50" en changeant «{taskID}» par l’ide précédemment noté de la tache.
9– Le changement a été fait. Le progrès du premier utilisateur (victime) a été changé. Pour voir la modification rafraichir la page de l’utilisateur victime.
CORRECTIF

Il faut ajouter un identifiant a la création de chaque tache décrivant le propriétaire de caque tache et ensuite faire une comparaison avec un if pour vérifier si c’est bien le créateur de la tache qui veut faire la modification. la comparaison ressemblerait un peu au code qui suit (se serais dans le fichier ServiceTaskImpl vers la ligne 77/78)

if (repo.findById(taskID).get().createur != repoUser)         {throw new IllegalAccessException();}
