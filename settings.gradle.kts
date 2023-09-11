pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Demo"
include(":app")
include(":core:remote")
include(":feature:articles")
include(":data:articles")
include(":feature:details")
include(":domain:articles")
include(":core:local")
include(":core:ui")
