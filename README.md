<h1 align="center">Projeto Pagamentos Futebol</h1>

## Descrição
Projeto desenvolvido para obtenção de nota para a disciplina de Desenvolvimento Web em 2023. O trabalho consiste em desenvolver um porjeto simplista para gerenciar pagamentos mensalistas em jogos de futebol.

## Informações sobre rotas
Rota de Jogador:
* <b>GET "/jogador"</b>: buscar todos os jogadores
* <b>POST "/jogador"</b>: criar um novo jogador, deve ser passador um JSON com as informações [nome, email e dataNasc]
* <b>GET "/jogador/{id}""</b>: buscar um jogador, deve ser informado um id em {id}
* <b>PUT "/jogador/{id}""</b>: atualizar dados de um jogador, deve ser informado um id em {id}, deve ser passador um JSON com as informações [nome, email e dataNasc]
* <b>DELETE "/jogador/{id}""</b>: deletar um jogador, deve ser informado um id em {id}
* <b>DELETE "/jogador""</b>: deletar todos os jogadores

Rota de Pagamento:
* <b>GET "/pagamento"</b>: buscar todos os pagamentos
* <b>GET ""/pagamento/jogador/{id}""</b>: buscar todos os pagamentos de um determinado jogador, deve ser informado o id do jogador em {id}
* <b>POST "/pagamento/{id}"</b>: criar um novo pagamento, deve ser informado o id do jogador em {id}, deve ser passador um JSON com as informações [ano, mes e valor]
* <b>GET "/pagamento/{id}""</b>: buscar um pagamento, deve ser informado um id de pagamento em {id}
* <b>PUT "/pagamento/{id}/{id_jogador}""</b>: atualizar dados de um pagamento, deve ser informado um id de pagamento em {id} e um id de jogador em {id_jogador}, deve ser passador um JSON com as informações [ano, mes e valor]
* <b>DELETE "/pagamento/{id}""</b>: deletar um pagamento, deve ser informado um id de pagamento em {id}
* <b>DELETE "/pagamento""</b>: deletar todos os pagamentos

## Tecnologias utilizadas
* Java;
* Spring (framework java);

## Colaboradores
<table>
  <tr>
    <td align="center">
      <a href="https://github.com/megrocholski">
        <img src="https://media.licdn.com/dms/image/C4E03AQF1nFCR3eZR3A/profile-displayphoto-shrink_200_200/0/1565954489999?e=1691625600&v=beta&t=Xzct_LSQ6efsNVgZTPo3kAAlavOynlujHQJ-grPPv1s" width="100px;" alt="Foto de Maria Eduarda do Linkedin"/><br>
        <sub>
          <b>Maria Eduarda G.</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/ferrp01">
        <img src="https://media.licdn.com/dms/image/C4D03AQGYHjCbGVMTMw/profile-displayphoto-shrink_200_200/0/1595522352566?e=1691625600&v=beta&t=EBwjNrHmTlb-QXnJ54z6LszSdUkFLHp3QV8NVsg8IMs" width="100px;" alt="Foto de Fernanda Ramos do Linkedin"/><br>
        <sub>
          <b>Fernanda Ramos P.</b>
        </sub>
      </a>
    </td>
  </tr>
</table>