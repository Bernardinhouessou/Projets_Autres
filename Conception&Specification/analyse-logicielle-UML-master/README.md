# Comment entreprendre une bonne analyse logicielle avec UML
-----------------------------
+Infos: https://openclassrooms.com/courses/debutez-l-analyse-logicielle-avec-uml
+ COURS sur MODELISATION: http://www.nicolasdanvin.com/gestion_de_projet/modelisation/48-48



**UML :** Unified Modeling Language ou Langage de Modélisation Unifié

## Introduction

UML est un langage visuel constitué d'un ensemble de schémas (Diagrammes).
Ces diagrammes donnent une vision différente du projet à réaliser.
Ces diagrammes représente le logiciel à développer (son fonctionnement, sa mise en route, les actions effectuées par le logiciel etc.

Réaliser les digrammes = Modéliser les besoins du logiciel à développer

UML n'est pas une méthode car il ne préconise aucune démarche.

UML est basé sur l'approche objet, démarche de gestion de projet :
- itérative et incrémentale
- guidée par les besoins du client et des utilisateurs
- centrée sur l'architecture du logiciel
- qui décrit les actions et les informations dans une seule entité

## Partie 1 - Les bases UML

Un projet informatique nécessite une phase d'analyse suivi d'une étape de conception

**Phase Analyse**
- Analyse des besoins : Diagramme de Contexte, Diagramme de Cas d'utilisation
- Analyse du domaine : Diagramme de Cas d'utilisation détaillé, Diagramme d'activité, Digramme de classes
- Analyse applicative : Diagramme de séquence, Diagramme d'état-transition, Digramme de collaboration

**Conception**
- Conception de la solution : Affinage de tous les diagrammes + Diagramme de composants et Diagramme de déploiement

### Les différents types de diagrammes

13 diagrammes officiels réalisés à partir du besoins des utilisateurs regroupés selon 2 aspects :
- Les aspects fonctionnels
- Les aspects liés à l'architecture

Ces 2 aspects sont représentés par le schéma de 4 vues (appelé 4+1 vues), axées sur les besoins des utilisateurs (Cas d'utilisation).

####Composition du 4+1 :

**Central :** Besoins des utilisateurs (1) - Coeur de l'analyse, description du contexte, des acteurs ou utlisateur du projet logiciel, les fonctionnalités du logiciel, les interactions entre acteurs et fonctionnalités

**Aspects fonctionnels :**
- Vue logique (2) - identifie les éléments du domaine, les relations er interactions entre ces éléments, organise les éléments du domaine en catégorie
- Vue des processus (3) - démontre la décomposition du système en processus et actions, les interactions entre les processus, la synchronisation et la communication des activités parallèles

**Architecture :**
- Vue des composants (4) - met en évidence les parties qui compose le futur système (fichiers source, bibliothèques, base de données, exécutables etc.
- Vue du deploiement (5) - décrit les ressources matérielles et la répartion des parties du logiciel sur ces éléments


####Les diagrammes

Diagramme utile dans l'analyse des besoins initiaux des utilisateurs (non officiel donc pas dans les 13) : **le diagramme de contexte**

**Besoins des utilisateurs (1)** : 
- Diagramme de packages : décompose le système en catégories ou parties plus facilement observables, indique également les acteurs
- Diagramme de cas d'utilisation : représente les fonctionnalités (dit cas d'utilisation) nécessaires aux utilisateurs, possibilité de faire ce digramme pour le logiciel entier ou pour un package

**Vue Logique (2) - Aspect fonctionnel du logiciel**
- Diagramme de classes : représente les entités (informations) manipulées par les utilisateurs, dans la phase de conception ce digramme représente la structure objet d'un développement orienté objet
- Diagramme d'objets : illustre les classes complexes en utilisant des instances

**Vue des processus (3) - Aspect fonctionnel du logiciel (3)**
- Diagramme de séquence : décrire les différents scénarios d'utilisation du système
- Diagramme d'activités : représente le déroulement des actions, sans utiliser d'objets, en phase d'analyse il consolide les spécifications d'un cas d'utilisation
- Diagramme de collaboration (ou Digramme de communication) : mettre en évidence les échanges de messages entre objets, servir à compléter les digrammes de séquence et de classes
- Diagramme d'état-transition : décrire le cycle de vie des objets d'une classe
- Diagramme global d'intéraction : donner une vue d'ensemble des interactions du système, même graphisme que le diagramme d'activité
- Diagramme de temps : analyse et conception de systèmes ayant des contraintes temps-réel, décrire intéractions entre objets avec contraintes temporelles fortes

**Vue des composants (4) - Aspect lié à l'architecture du logiciel**
- Diagramme de structure composite : décrire un objet complexe lors de son exécution
- Diagramme de composants : décrire tous les composants utiles (applications, librairies, DB etc) à l'exécution du système

**Vue de déploiement (5) - Aspect lié à l'architecture du logiciel**
- Diagramme de déploiement : description de l'environnement d'exécution du système (matériel, réseau etc.), comment les composants sont installés

### Proposition d'une démarche de travail

**Rappel :** UML ne préconise aucune démarche

**Étapes de développement d'un logiciel :**

1. Définir les besoins et les exigences du client et des utilisateurs
2. Analyser le système 
3. Concevoir le système 
4. Programmer le logiciel
5. Tester le logiciel
6. Déployer 
7. Maintenir le système

Projet géré avec le principe d'itération : plusieurs allers-retours sur les diagrammes avant d'avoir un dossier d'analyse complètement satisfaisant

* La définition d'un logiciel divisée (en gros) en 2 parties : *
- Étape de l'analyse (des besoins, du domaine, applicative)
- Conception de la solution

**Étape analyse**
- Définir les besoins (contexte et système)
  - Diagramme de contexte (identifier les acteurs)
  - Diagramme de cas d'utilisation (indiquer comment les acteurs utilisent le système)
- Analyse de domaine
  - Diagramme de cas d'utilisation (détailler les fonctionnalités)
  - Diagramme d'activité (décrire le déroulement des cas d'utilisations)
  - Diagramme de classes (préciser les informations nécessaires pour les actions d'un cas d'utilisation)
- Analyse applicative (ou analyse de la solution)
  - Diagramme de séquences (détailler le déroulement d'un cas d'utilisation en indiquant les informations à utiliser)
  - Diagramme d'état-transition (identifier tous les états par lequel un objet peut passer, trouver les actions qui permettent d'obtenir un changement d'état)
  - Diagramme de collaboration (ou communication) (identifier les messages échangés entre objets, trouver de nouvelles actions)

**Étape conception**
- Conception de la solution
  - Tous les diagrammes (Vérifier la cohérence des diagrammes, apporter des détails nécessaires à l'implémentatio de la solution)
  - Diagramme de composants (Indiquer comment le logiciel sera construit)
  - Diagramme de déploiement (montrer sur quel matériel chacun des composants devra être installé, indiquer les éventuels moyens de communication entre les parties)

### Résumé
Dans une démarche de modélisation d’un projet informatique avec UML, **les points clés** sont :
- une démarche guidée par les besoins utilisateurs,
- une démarche itérative et incrémentale,
- une démarche centrée sur l’architecture logicielle,
- une mise en évidence des liens qui existent entre les actions et les informations.

