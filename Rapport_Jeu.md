# BatailleNavale

Ce jeu a été réalisée dans le cadre de la Licence "Projet web : Développement et communication multimédia" à Sorbonne Universités.

![Alt text](bn.png?raw=true "Optional Title")

*Sommaire :*
------------
```Java
I - Le Jeu 

II - Libgdx

   a. Présentation
  
   b. Extensions
  
III - Les fonctionnalités

IV - Conclusion
```

*I - Le jeu :*
--------------

Il s'agit d'un jeu Bataille navale, chaque joueur doit tout d'abord placer ses différents bateaux puis deviner la position des bateaux adverses afin de les faire couler, le premier joueur ayant fait couler tous les bateaux de l'adversaire, remporte alors la partie.

*II - Libgdx*
-------------

  **a. Présentation**
  
Nous avons utilisé Libgdx durant le développement de notre application. Il s'agit d'un framework de développement de jeux gratuits développé en Java mais aussi une bibliothèque multi-plateforme performante.
Celui-ci possède différentes extensions qui ont été nécessaires à la programmation d'un jeu comme celui de la Bataille Navale que nous avions à faire.

  **b. Extensions**

scene2d qui est un graphe de scène en 2D pour créer des applications et des interfaces utilisateur en utilisant une hiérarchie d'acteurs.
Cependant nous avons rencontré quelques difficultés lorsque l'ont souhaitait l'utiliser pour la partie "Gameplay". Nous avons donc utilisé les classes de base proposées par libGDX

*III - Les fonctionnalités*
---------------------------

Ce jeu possède différentes classes :

**TheGame.java**

  Il s'agit d'une classe qui étend Game de LibGDX, c'est l'instance maître du jeu. Elle s'occupe uniquement de charger le menu principal.

**MainMenu.java**

  Il s'agit d'une classe qui étend ScreenAdapter de LibGDX, c'est l'équivalent d'un écran. C'est la seule classe où l'on utilise scene2d.

  On y crée une scène avec une image de fond, et deux boutons, l'un qui permet d'entrer dans la phase "Gameplay" en chargeant la classe **BN.java**.
  
  Il y a aussi une musique de fond, et des animations d'apparitions sur les éléments de la scène.
  Les actions de charger la phase "Gameplay" ou de quitter le jeu sont gérer par l'input processor qui est ajouté à la scène. Les listener sont ensuite ajoutés aux boutons.

**BN.java**

  C'est la classe principale du jeu où l'on trouve le déroulement du jeu ainsi que les différentes actions qui peuvent exister durant la partie en cours.

  Cette classe étend la classe ScreenAdapter de LibGDX. Il est chargé lors de l'appui d'un bouton sur le menu principal du jeu. Elle charge toutes les ressources nécessaires à la phase "Gameplay" et les classes Grid. Elle met en place la partie et gère les autres classes nécessaires au bon fonctionnement du jeu. La classe **BN.java** permet de piloter l'affichage du jeu en fonction de l'état de la partie. C'est elle aussi qui écoute le déplacement de la souris du joueur afin d'y placer le marqueur sur le jeu. 
  
**Grid.java**

  Il s'agit d'une classe de base qui permet de représenter la grille de base. Elle gère les affichages mais aussi les actions comme les tirs loupés, l'affichage des bateaux touchés ...

**Grid_player.java**

  Cette classe étend **Grid.java**, elle permet l'affichage de la grille du joueur. La classe permet au joueur de positionner ses bateaux et de les voir afficher. Sinon elle fonctionne de la même manière que la classe **Grid.java**

**Boat.java**

  La classe boat est une classe parente des autres classes représentant des bateaux :

- 2 Destructeurs (2 cases)

- 1 Sous-marin (3 cases)

- 1 Croiseur (3 cases)

- 1 Cuirasser (4 cases)

- 1 Porte-avions (5 cases)

  Elle permet d'afficher les bateaux et s'ils ont subi des dégâts en fonction de la position du tir précédent.

**IA.java**

  Qui contrôle l'adversaire, il possède un objet Grid qu'il contrôle en fonction de l'état de son tir précédent, s'il n'a pas touché de case il se comporte aléatoirement. Sinon il va essayer de deviner la position du bateau et le détruire.

**DesktopLauncher.java**

  C'est la classe qui permet de lancer le jeu, Elle permet de définir la classe Jeu que l'on lance avec certains paramètres. On pourrait la représenter par la création de la fenêtre qui contient le jeu.

*IV - Conclusion :*
--------------

Ce projet nous a permis d'en apprendre davantage sur le langage de programmation Java ainsi que la bibliothèque Libgdx que nous ne connaissions pas avant. Nous avons également pu améliorer différents aspects comme le travail en équipe, ainsi que l'élaboration d'un projet à travers GitHub. 

