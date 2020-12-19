https://www.syncfusion.com/kb/wpf
https://docs.telerik.com/devtools/wpf/introduction
https://www.telerik.com/support/wpf
https://fr.wikipedia.org/wiki/Windows_Communication_Foundation
https://docs.telerik.com/devtools/wpf/common-information/consuming-data/wcf-service

Q:A QUOI SERT WCF ?

R: -------------------------------------------------------------------
WCF (Windows Communication Foundation) est un sous-système de communication de Windows Vista
 (l'ancien nom de code était Indigo).
 Les applications WCF peuvent être développées en utilisant les différents langages de Microsoft .NET.

C'est l'un des quatre composants majeurs du framework .NET 3.0 (avec WPF, CardSpace et WF)
, qui est inclus dans Windows Vista et Windows Server 2008. 
Il est possible aussi d'installer ce composant sur Windows XP et Windows Server 2003. 

-------------------------------------------------------------------
Service WCF

Un service WCF est composé de trois parties

    une classe service
    un environnement hôte
    un ou plusieurs points finaux

Vue d'ensemble
-------------------------------------------------------------------
Le modèle de programmation WCF est une couche d'abstraction qui unifie et simplifie la mécanique d'intégration des services Web, .NET Remoting, Microsoft Transaction Server, et Microsoft Message Queuing.

Cette couche permet en outre la redistribution des rôles:

    Le développeur conçoit et développe son service sans se soucier de son implémentation à cible. C'est-à-dire qu'il ne s'intéresse qu'aux caractéristiques structurantes du service pour son intégration au sein d'une Architecture orientée services : le service fonctionne-t-il en mode singleton, en mode asynchrone, avec un callback, etc.
    L’intégrateur (ou l’administrateur), lui, détermine le protocole mais aussi le niveau et le mode de sécurisation du service ainsi développé.

WCF utilise des messages SOAP pour les communications entre processus. Quand un processus WCF discute avec un processus non WCF, le langage XML est utilisé pour les messages SOAP. Pour les messages entre processus WCF, les messages SOAP sont encodés au format binaire. 