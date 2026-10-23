# Meus Filmes — Android Kotlin / MVVM

## Como abrir
1. Extraia o ZIP.
2. Abra a pasta `MeusFilmes` no Android Studio.
3. Aguarde o Gradle Sync.
4. Execute em um emulador ou dispositivo Android.

## Recursos
- Lista inicial de filmes favoritos.
- Edição de título, nota (0 a 10) e URL do pôster.
- View Binding em todos os layouts.
- MVVM com ViewModel + StateFlow.
- Coil 3 para carregar imagens em ImageView.

## Observações
- As edições ficam em memória durante a execução; para persistência após fechar o app, adicione Room/DataStore.
- As imagens usam URLs HTTPS. O emulador/dispositivo precisa de internet.
- O projeto usa Gradle Kotlin DSL. Se seu Android Studio pedir atualização de Gradle/Android Gradle Plugin, aceite a versão compatível sugerida pelo IDE.
