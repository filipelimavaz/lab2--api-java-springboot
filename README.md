# :wave: Laboratório - roteiro 2 - API com modelagem dos dados (relações JPA)

## 🤓 O que vamos aprender? 

* Aprender a escrever APIs com dados persistentes usando um esquema de dados relacional e modelagem de relacionamentos JPA.

### Tecnologias envolvidas:
* ORM - Mapeamento objeto relacional (Hibernate é a implementação por trás do que usaremos)
* JPA - interface unificada para facilitar mapeamento de objetos para registros de tabelas e definir relações entre entidades

Lembrete: use o [spring initizlizr](https://start.spring.io) para criar seu projeto spring dentro ou fora da IDE. Dessa vez marque as dependências "_Spring Web Starter_", "_H2 Database_" e "_Spring Data JPA_" na configuração do seu projeto (além de "_Lombok_" e "_DevTools_").

Neste segundo lab o design da API REST a ser desenvolvida será dado novamente, na verdade, é muito parecido com o primeiro. Continuaremos o desenvolvimento do primeiro lab no contexto de courses. Mas agora vamos adicionar persistência, vamos iniciar todas as courses de uma vez. Relembrando, por enquanto, no contexto da nossa API, uma **Disciplina** é uma classe que tem os seguintes atributos: **id:long**, **nome:String**, **notas:List<Double>** e **likes:int**. Para este lab vamos adicionar algo mais... É possível associar comentários a courses. Assim, *comments:List\<Comentario>* passa a ser mais uma informação associada à course. 

Um **Comentario** deve ter o seguinte estado: **id:long**, **dataDoComentario:LocalDate**, **texto:String**, **removido:boolean**, *course:Disciplina*. Cada course pode estar associada a muitos comentários, mas cada comentário está associado a apenas uma course. Com essa nova funcionalidade vamos adicionar várias novas rotas na nossa API para o CRUD de comentários.

Também vamos começar a ter a noção de **Tag**. Uma tag é uma palavra (que pode ser simples ou composta) que os alunos que avaliam uma course podem usar para caracterizar a course. Uma course então passa a estar associada a uma lista de tags que a representam, como, por exemplo: massante, muito teórica, rasgada, difícil, etc. A base de tags deve ser populada à medida que as courses vão sendo tagueadas, assim, não deve ter na base de tags repetição de termos já usados. Cada tag pode estar associada a muitas courses, e cada course também pode estar associada a muitas tags. 

O objetivo desta API é permitir que alunos comentem e deem likes nas courses do curso de Sistemas de Informação. 

### Povoando a base de courses:
Temos um arquivo JSON [aulas/disciplinasSI.json](https://github.com/raquelvl/psoft/blob/master/aulas/disciplinasSI.json) já com os nomes de todas as courses que devem ser criadas. A ideia é programar sua API para povoar o banco de dados com todas as courses já existentes neste arquivo. [Neste documento](http://bit.ly/inicia-dados-json) encontra-se uma discussão sobre como ler dados de um json e adicionar ao banco de dados usando spring boot (você vai ter que entender e implementar o seu próprio). Lembre que a própria API deve se encarregar de gerar os identificadores únicos das courses no banco (@GeneratedValues). Com isso, não precisaremos mais de uma rota na API para adicionar courses. Outro lembrete: essa atividade envolve já o uso do banco, então você deve criar o repositório de Disciplinas e também o de comentários, marcar as classes que vão estar associadas ao banco como @Entity, e já deve ter configurado o banco em application.properties. (para testar você pode usar a rota GET /api/courses que retornará todas as courses inseridas no sistema).

### Use Spring Boot e java para desenvolver a seguinte API:

GET /api/courses 
Retorna um JSON (apenas com campos id, nome) com todas as courses inseridas no sistema e código 200. 

GET /api/courses/{id}
Retorna um JSON que representa a course completa (id, nome, nota média, número de likes e os comentários) cujo identificador único é id e código 200. Ou não retorna JSON e código 404 (not found) caso o id passado não tenha sido encontrado. 

PATCH /api/courses/likes/{id}
Incrementa em um o número de likes da course cujo identificador é id. 
Retorna a course que foi atualizada (incluindo o id, nome e likes) e código 200. Ou não retorna JSON e código 404 (not found) caso o id passado não tenha sido encontrado.

PATCH /api/courses/nota/{id}
Adiciona uma nova nota à lista de notas da course de identificador id no sistema. No corpo da requisição HTTP deve estar um JSON com uma nova nota atribuída à course. A nova nota da course deve ser calculada como a média de todas as notas já recebidas, incluindo a nova nota passada nesta chamada. Se for a primeira nota sendo adicionada então esta nota é a que vai valer para a course. 
Retorna a course que foi atualizada (incluindo o id, nome e nota média) e código 200. Ou não retorna JSON e código 404 (not found) caso o id passado não tenha sido encontrado. 

POST /api/courses/{id}/comments
Insere um novo comentário na course de identificador id. No corpo da requisição HTTP deve estar um JSON com o novo comentário a ser adicionado na course a ser atualizada no sistema. 
Retorna a course que foi atualizada (incluindo o id, nome e os comments atualizados) e código 200. Ou não retorna JSON e código 404 (not found) caso o id passado não tenha sido encontrado.

GET /api/courses/{id}/comments
Retorna todos os comentários associadas à course de identificadir id e código de resposta 200. Ou não retorna JSON e código 404 (not found) caso o id passado não tenha sido encontrado. Aqui deve ser possível usar algum parrâmetro que filtre os comments que contiverem algum padrão (usar @RequestParameter) se o usuário desejar.

POST /api/courses/{id}/tags
Insere uma nova tag associada à course de identificador id. No corpo da requisição HTTP deve estar um JSON com a tag a ser adicionada na course a ser atualizada no sistema. 
Retorna a course que foi atualizada (incluindo o id, nome e as tags atualizadas) e código 200. Ou não retorna JSON e código 404 (not found) caso o id passado não tenha sido encontrado.

GET /api/courses/ranking/notas
Retorna todas as courses inseridas no sistema ordenadas pela nota (da maior para a menor) e código 200.

GET /api/courses/ranking/likes
Retorna todas as courses inseridas no sistema ordenadas pelo número de likes (da que tem mais likes para a que tem menos likes) e código 200.

GET /api/courses/{id}/tags
Retorna todas as tags associadas a course de código id e código 200. Ou não retorna JSON e código 404 (not found) caso o id passado não tenha sido encontrado.

GET /api/courses/tags (?tag=str)
Retorna todas as courses associadas à tag informada no parametro de busca (@RequestParameter) e código 200. Se nenhuma tag for informada não retorna nada.

Para todas as funcionalidades dessa API lembre de realizar o tratamento adequado de erros seguindo o que estudamos em sala (detalhes do problema - RFC 7807) e @RestControllerAdvice.

Seguem algumas dicas:

* Use o padrão DAO para acesso às bases de dados;
* Siga boas práticas de design, buscando desacoplamento utilize corretamente controladores, serviços e repositórios;
* Organize suas classes em packages com nomes significativos (xx.services, xx.controllers, xx.repositories, xx.entities, etc. - pode usar nomes em portugues também, mas mantenha a coerência, ou tudo em portugues ou tudo em ingles);
* Para ordenação aprenda a definir um novo método no repositório de course seguindo o padrão de nomes dos métodos. Mais dicas [aqui](https://www.baeldung.com/spring-data-sorting).

**Não faça tudo de uma vez**. Desenvolva uma funcionalidade, teste, vá para a próxima… 🚀
