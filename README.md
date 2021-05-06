# Teaching-HEIGVD-RES-2021-Labo-SMTP

Laurent Tailhades & Corentin Zeller

## Description du labo

Le but du labo est de générer un nombre d'émail qui sera déinit à partir de plusieurs fichiers de configuration comprenant notamment une liste d'addresse mail et une liste de message

Le programme se connecte en local ("localhost") sur le port 25 sur un serveur MockMock qui sera lancé dans un conteneur Docker pour simuler un serveur SMTP.

## Instrustion

### Remplissage des fichiers de configuration

Il faut rentrer manuellement les 3 fichiers suivant pour que le programme genère correctement les emails.

- le fichier config.properties contient l'adresse et le port du serveur SMTP ainsi que le nombre de groupe à piéger
- le fichier message.utf8 qui va contenir le sujet du message à la première ligne et le corps du message, les caractère spéciaux sont supporté et il n'y pas besoin de spécifé que c'est un sujet pour la première ligne.
- le fichier victims.utf8, qui va contenir toutes les addresses mail à pieger. 

### Mise en place et utilisation du serveur Mock

Mock server  permet de tester des envois de mails sans les envoyer réellement. Le serveur  mock  va recevoir les mails envoyés et les afficher sur une interface web sont qu'ils soient réellement envoyés à leur destinataire.

#### pour le lancer

Dans le dossier MockMock il y une image docker que l'on va devoir construire avec la commande :

- $ docker build -t res-mockmock .

Quand l'image est construite on va utilise la commande suivante pour lancer le serveur mock:

- $ docker run -p 2525:2525 -p 8282:8282 res-mockmock

Ici, le serveur SMTP sera disponible sur le port 2525 et l'application web sera consultable à l'adresse "localhost:8282"






