# Replicação de EnyueYang et. al 2021
 
 ## Instrução de replicação
 
 Para replicar o algorítimo do Artigo FCMF: Federated collective matrix factorization for heterogeneous
collaborative filtering, basta copiar esse repositório colocar as rotas certa para o DataSet no arquivo Data.java e rodar o arquivo Main.java.
 
 
 ## Estudo original

O processo de decentralização dos dados é algo muito discutido nos dias que correm. Considerando os inúmeros vazamentos e leis que vem surgindo como a LGPD, o processo de aprendizagem federativa para modelos de aprendizagem de máquina se torna uma técnica cada vez mais comum.

Para se entender melhor, o processo de aprendizagem federativa, imagine o sistema de recomendação da Netflix, atualmente esse sistema capta os dados de todos os usuários e centraliza em um determinado servidor para realizar o processo de treinamento e recomendação para os seus usuários, esse pratica torna os dados muito vulneráveis considerando que apenas uma invasão a esse servidor pode vazar milhares de informações. A aprendizagem federativa ajuda no processo de decentralização desses dados aplicando através de treinamento de modelos locais que ficam localizados nos dispositivos de usuários e envia para o modelo global centralizado apenas gradientes desses modelos treinados sem compartilhar os dados utilizados.

Porém, alguns estudos já vem provando que a maneira que é feita usando o gradiente desses modelos pode ser facilmente entendida pelo invasor que consegue inferir comportamento dos usuários a partir de recomendações ou predições estabelecidas presentes no gradiente compartilhado.

Desse modo, o artigo proposto por EnyueYang et. al 2021 traz uma solução para esse problema chamado de  federated collective matrix factorization (FCMF), essa abordagem proposta visa promover um sistema de recomeçadão que considera apenas dados menos sensíveis para o compartilhamento via gradiente e ainda utiliza de criptografia homomórfica e privacidade diferencial para proteger esses dados compartilhados e assim evitando que invasores consigam captar essas informações. 

Para uma melhor compreensão sobre às técnicas e algorítimo utilizadas, Matriz de Fatoração é um algorítimo muito utilizado para sistemas de recomendação em que esse processo e dado por uma representação de dados em formato de matrizes, podendo ser representados de maneira clássica como matriz de usuários e itens, para entender melhor como funcionar sugiro que [leia esse post.](https://medium.com/analytics-vidhya/matrix-factorization-made-easy-recommender-systems-7e4f50504477) Já a Privacidade diferenciada, grosso modo usa algum ruído aleatório para evitar uma violação da privacidade, removendo as características individuais, mas preservando as estatísticas características.

[Aqui está o link para o artigo completo.](https://www.sciencedirect.com/science/article/abs/pii/S0950705121002094)

## Objeto da reprodução (ou reanálise)

O objetivo em questão é a reprodução do experimento exposto pelo autor em relação aos modelos comparativos em determinado conjunto de dados. Em resumo, o objetivo é chegar em uma tabela demostrada na Figura\ref{fig:result1}.

Para entender melhor a tabela, observa que o autor realizar um comparativo entre Sistemas de Recomendação que usam técnicas de treinamento a partir de dados centralizados como o RSVD e CMF contra o proposto que é o FCMF, para essa comparação foi utilizado os dados ML100K que contém um conjunto de usuários, filmes e avaliação de cada filme por usuário. O autor também expõe o alfa e beta usado nos paramentos dos modelos e como métrica de comparação ele utiliza RMSE e MAE.

Os resultados apresentados, nos mostram que o modelo FCMF consegue ter eficiência próxima em comparação com os modelos de aprendizagem centralizada, mesmo aplicando o processo de decentralização dos dados e criptografia, portanto, o que é exposto é uma estrutura de sistema de recomendação eficiente e segura para ser adotada. 

## Metodologia original

### Coleta de dados
Os autores utilizam uma amostra de 100 mil avaliações de usuários em relação aos filmes da plataforma MoviLeans (ML100K) dividido em duas amostras de treino e teste. E os dados que são coletados é as recomendações dos algorítimos FCMF, CMF e RSVD para esse conjunto de teste.

### Dados gerados
Para essa etapa é gerada uma pontuação que o modelo acredita que um determinado usuário X possa ter dado a um determinado filme, essa pontuação pode variar entre 1 a 5, como mostra a Tabela abaixo:

Modelo   | Filme | Pontuação
--------- | ------ | ------
RSVD | StarWars | 4
CMF | StarWars | 5
FCMF | StarWars | 5

Essa tabela fornecida para cada usuário da base teste serve como comparativo para ao resultado real que temos e assim poder gerar as métricas. 
### Análise de dados

No artigo original os autores captam os resultados obtidos nos três modelos na base de teste, e aplica as métricas MAE e RMSE para avaliar os modelos nessa etapa.

Para entender melhor essas métricas:

{Mean Absolute Error} (MAE): MAE é a medida do
erro entre a pontuação prevista e a pontuação verdadeira
pontuação nos dados do teste. 
{Root Mean Square Error} (RMSE): RMSE é a raiz quadrada do erro entre a pontuação prevista e a pontuação verdadeira nos dados do teste. É mais sensível aos resultados com grandes erros do que o MAE.

Então o que o artigo faz e coletar os valores de recomendação de cada modelo e compara com os valores reais da base de teste e assim aplica nas métricas acima, após esse processo ele sumariza os resultados para comparação entre os modelos testados como mostra a Figura

## Diferenças metodológicas com o estudo original

Para nossa análise, iremos reproduzir o modelo FCMF proposto no artigo, usando os mesmos paramentos e a base de dados ML100K. Desse modo, não iremos aplicar a reprodução dos modelos existentes (RSVD e CMF) e como referência iremos utilizar a métrica MAE.

Nosso objetivo é observar se o modelo realmente consegue chegar a esse resultado de MAE como exposto, para isso, iremos rodar o modelo treinado e aplicar bootstrap para cada uma das recomendações do teste e calcular o seu valor de MAE para cada interação, o objetivo desse processo é conseguir com 95\% de confiança o valor de MAE e poder comparar com os resultados expostos pelos autores.

## Resultado da replicação/reanálise
Replicando o estudo que detalhamos anteriormente e considerando \alpha  \beta = 0.001.

Obtivemos o seguinte resultado:



O gráfico  nos mostra com um valor de 95\% de confiança que os valores de MAE varia entre 0,7636 e 0,7643 com um valor médio de 0,7639. 

## Replicação vs. estudo original

Considerando os resultado obtido podemos observar uma diferença considerando que o valor médio relatado pelos autores são de 0,7481, os valores são próximos, mas temos uma diferença.

Um ponto notado é que no código o autor usa 0,01 com referência de alpha e beta, mas na tabela ele expôs 0,001 para vê se isso tem algum impacto rodamos novamente o experimento, porém mudando esse paramento.

Observamos no gráfico mostrado  que os valores ficam muito próximos ao exposto no artigo, com um valor médio de 0,75.Isso pode nos dar um indício que o autor pode ter se confundido ao colocar os paramentos na tabela.






