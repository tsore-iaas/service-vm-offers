# VM Offers Service

Ce service fait partie du projet FireCracker-Prod et gère les offres de machines virtuelles (VM). Il permet de créer, lire, mettre à jour et supprimer des offres de VM via une API REST.

## Prérequis

- Java 17 ou supérieur
- Maven
- MySQL/PostgreSQL (selon votre configuration)

## Configuration

1. Clonez le repository :
```bash
git clone <repository-url>
cd service-vm-offers
```

2. Configurez la base de données dans `cloud-conf-firecracker` :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/service-vm-offers?useSSL=false&serverTimezone=\UTC&useLegacyDatatimeCode=false
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

## Démarrage du Service

1. Compilez le projet :
```bash
mvn clean install
```

2. Lancez l'application :
```bash
mvn spring-boot:run
```

Le service démarrera sur `http://localhost:8083` par défaut.

## Structure du Projet

- `model/`: Contient les entités JPA
- `repository/`: Contient les interfaces de repository
- `service/`: Contient la logique métier
- `controller/`: Contient les endpoints REST
- `dto/`: Contient les objets de transfert de données

## API Endpoints

### Format de Réponse Standard
Toutes les réponses API suivent ce format :
```json
{
    "status": "success/error",
    "message": "Description du résultat",
    "data": { ... }
}
```

### Endpoints Disponibles

1. **Créer une offre VM**
   - POST `/api/vm-offers`
   ```json
   {
       "name": "Offre Standard",
       "description": "VM standard pour usage général",
       "cpu_count": "2",
       "memory_size_mib": "2048",
       "disk_size_gb": "20",
       "price": "10.99",
       "is_active": "ACTIVE"
   }
   ```

2. **Obtenir toutes les offres**
   - GET `/api/vm-offers`

3. **Obtenir une offre spécifique**
   - GET `/api/vm-offers/{id}`

4. **Mettre à jour une offre**
   - PUT `/api/vm-offers/{id}`
   ```json
   {
       "name": "Offre Standard Updated",
       "description": "Description mise à jour",
       ...
   }
   ```

5. **Supprimer une offre**
   - DELETE `/api/vm-offers/{id}`

## Modèle de Données

Le service utilise le modèle `VmOffers` avec les champs suivants :
- `id`: Identifiant unique
- `name`: Nom de l'offre
- `description`: Description de l'offre
- `cpu_count`: Nombre de CPU
- `memory_size_mib`: Taille de la mémoire en MiB
- `disk_size_gb`: Taille du disque en GB
- `price`: Prix de l'offre
- `is_active`: Statut de l'offre (ACTIVE/INACTIVE)
- `createdAt`: Date de création

## Tests

Pour exécuter les tests :
```bash
mvn test
```

## Support

Pour toute question ou problème, veuillez créer une issue dans le repository GitHub.
