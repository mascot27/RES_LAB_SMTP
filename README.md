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

### Mise en place du serveur Mock





