# Teaching-HEIGVD-RES-2021-Labo-SMTP

Laurent Tailhades & Corentin Zeller

## Description du labo

Le but du labo est de générer un nombre d'émail qui sera déinit à partir de plusieurs fichiers de configuration comprenant notamment une liste d'addresse mail et une liste de message

Le programme se connecte en local ("localhost") sur le port 25 sur un serveur MockMock qui sera lancé dans un conteneur Docker pour simuler un serveur SMTP.

## Instruction

### Remplissage des fichiers de configuration

Il faut rentrer manuellement les 3 fichiers suivant pour que le programme genère correctement les emails.

- le fichier config.properties contient l'adresse et le port du serveur SMTP ainsi que le nombre de groupe à piéger

![interieur fichier config](https://user-images.githubusercontent.com/58049740/117338767-3d839a00-ae9f-11eb-8df3-b82b3f88b850.PNG)

- le fichier message.utf8 qui va contenir le sujet du message à la première ligne et le corps du message, les caractère spéciaux sont supporté et il n'y pas besoin de spécifé que c'est un sujet pour la première ligne.

![interieur fichier message](https://user-images.githubusercontent.com/58049740/117338810-4ffdd380-ae9f-11eb-93d2-7d0df216de64.PNG)

- le fichier victims.utf8, qui va contenir toutes les addresses mail à pieger.

![intérieur fichier mail](https://user-images.githubusercontent.com/58049740/117338817-51c79700-ae9f-11eb-810c-787a5af95edb.PNG)



### Mise en place et utilisation du serveur Mock

Mock server  permet de tester des envois de mails sans les envoyer réellement. Le serveur  mock  va recevoir les mails envoyés et les afficher sur une interface web sont qu'ils soient réellement envoyés à leur destinataire.

####  Pour lancer le serveur Mock

Dans le dossier MockMock il y une image docker que l'on va devoir construire avec la commande :

- $ docker build -t res-mockmock .

Quand l'image est construite on va utilise la commande suivante pour lancer le serveur mock:

- $ docker run -p 2525:2525 -p 8282:8282 res-mockmock

Ici, le serveur SMTP sera disponible sur le port 2525 et l'application web sera consultable à l'adresse "localhost:8282"

### Lancement de l'application PrankApp

PrankApp est une application Java, pour la lancer il faut donc :

- Posséder Java sur son ordinateur, ici java 8 suffit.
- Se position dans le dossier racine .
- Exécuter la commande "java -jar PrankApp.jar".

On pourra voir le résultat de notre execution en visitant l'application web du serveur MockMock qui devrait afficher les emails envoyés

![prevueMailWebMock](https://user-images.githubusercontent.com/58049740/117339930-9c95de80-aea0-11eb-954a-2bad250caef9.PNG)


La console du serveru Mock va lui aussi afficher qu'un email est bien reçu


![preve recu mock](https://user-images.githubusercontent.com/58049740/117339910-9869c100-aea0-11eb-86b1-c6f29362ed72.PNG)

## Descritpion de l'implémentation

### Diagramme de classe

![ulm](https://user-images.githubusercontent.com/58049740/117341629-ab7d9080-aea2-11eb-8c19-1a436e1a5d0f.PNG)


Nous avons 5 package

#### prankMessage

Contient les classes permettant de lire le fichier de message ainsi que de le construire et de différencier le corps du sujt.

#### prankCampagne

Contient les classes pour générer les groupes de chaque différente campagne de faux messages.

#### victims

Contient les classes permettant de lire le fichier de victimes 

#### mail

Contient les classes qui vont forger les emails pour qu'ils puissent être ensuite correctement envoyé sur un serveur SMTP.

#### config

Contient les classes qui permettront de recevoir les bonnes valeurs de l'addresse ainsi que le port du serveur SMTP avec lequel communiquer


PrankApplication est le main qu'il suffira d'exécuter pour que l'application fonctionne correctement.
la console affichera seulement comment les détails des mails qui sont envoyé sur le serveur ainsi que la réponse du serveur.

![preuve envoir intelij](https://user-images.githubusercontent.com/58049740/117343433-9570cf80-aea4-11eb-9683-e7a1ee3aa510.PNG)





