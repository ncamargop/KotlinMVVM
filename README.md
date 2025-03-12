# Kotlin-App

Link hacia la Wiki del equipo https://github.com/ISIS-3510-G42/Backend-App/wiki/Home-%E2%80%90-ISIS3510-Documentation




# Uso de gitflow:

1. Asegurarte de estar en develop y actualizarlo:
```bash
git checkout develop
git pull origin develop
```

2. Crea una nueva feature:
```bash
git flow feature start nombre-de-la-feature
```

3. Trabajar sobre la feature, luego haces commit:
```bash
git add .
git commit -m "Descripción del cambio"
```

4. Subir la feature a github
```bash
git push origin feature/nombre-feature
```

5. Cerrar la rama de la feature:
```bash
git flow feature finish nombre-de-la-feature
````
Esto te redirige inmediatamente a develop, sino haz checkout a develop.

6. Hacer el push de develop:
```bash
git push origin develop
``` 
