# The Alley pub B-E

Rappresentare la parte B-E della gestione dei prodotti di vendita presenti in un Irish Pub che vole poter gestire in maniera autonoma la __creazione__, __modifica__, __eliminazione__ dei prodotti esposti sul sito vetrina. 

Organizzazzione della parte Back-end con java, attraverso l'uso di Spring Boot e maven

Entità da rappresentare:
- Principali
    - **Cibo**
        - id
        - nome
        - descrizione
        - prezzo
    - **Bevande**
        - id
        - nome
        - descrizione
        - dimensione
        - prezzo
- Collegate
    - **Ingredienti** collegamento N-N con cibo
        - id
        - nome
        - descrizione
    - **Categoria** collegamento N-N con Cibo e con Bevande
        - id
        - nome
