## Trabalho Avaliativo da disciplina Sistemas Distribuidos do curso Sistemas de Informação da UFPA
### Descrição do trabalho

Trabalho NFS + Serviços WEB
Atualmente serviços de compartilhamento estão em evidencia em função de atividades
home office . Portanto, criar metodologia para criação de compartilhamentos de recursos
tornam-se essenciais, neste sentido, principalmente em função da heterogeneidade dos
dispositivos, logo, o aluno deve criar aplicação WEB e mobile com interface para enviar
e receber arquivos para o servidor apontando para uma pasta compartilhada na rede
através do NFS. Para atribuir o serviço é necessário instalar e configurar o apache,
servidor NFS, cliente NFS. No NFS criar uma pasta que ficará disponível na rede para os
clientes NFS consultar. 

Aplicação web pode ser feita em qualquer linguagem de programação, onde deverá ter o
campo de upload para inserir o arquivo que será compartilhado em uma pasta da rede
pelo NFS, após o envio, aplicação deverá listar os arquivos da pasta na ambiente web
(Navegador), tendo a opção para excluir o arquivo.
Quando o arquivo for enviado deverá ser encaminhado para a pasta compartilhada que
foi previamente configurada e os usuários da rede passarão a ter o acesso através do NFS,
onde poderão abrir ou excluir (Leitura e Escrita).

Procedimento para execução do trabalho:

1. Preparar o ambiente de servidores de arquivos:

   1.1. Instalar o servidor NFS;
  
   1.1.1. Configurar pasta que será compartilhada (/home/usuário/compartilhados)

   1.2. Instalar as Máquinas NFS’s Clientes

   1.2.1. Configurar o mapeamento para a pasta compartilhada no servidor com as
devidas permissões de acesso.

2. Preparar o ambiente Web:

   2.1. Instalar o servidor HTTP com PHP ou FTP ou Tomcat– como, por exemplo o
XAMPP para Linux/Windows

   2.1.1. Adicionar a aplicação responsável pelo upload, o qual deverá gravar o
arquivo na pasta compartilhada no servidor (/home/usuário/compartilhados).
Para evitar problemas de permissão crie o arquivo com CHMOD 777.

   2.1.2. Após submeter o arquivo a página de retorno devera listar os arquivos
disponibilizados no servidor com o link para excluir!


ATENÇAO: deverá ser apresentado um artigo com conceitos (Fundamentação Teórica)
os procedimentos feitos do início ao fim do trabalho (Metodologia) e a conclusão. Por
exemplo: quais os procedimentos feitos para instalar e configurar o NFS no servidor e no
cliente, os procedimentos para configuração do apache (servidor HTTP - o XAMPP
instala e configura o ambiente.), em seguida, instalar a aplicação em PHP (basta adicionar
no directory Index (pasta htdocs ou www)), por fim, no cliente, como acessar a pasta.

Trabalho em grupo de até 5 alunos.

Artigo com layout SBC ou IEEE (de 5 a 10 páginas) e apresentação de até 15 minutos.

Vale uma avaliação e deverá ser entregue e apresentado no dia 26/10/2022

![image](https://user-images.githubusercontent.com/77124683/200061126-e7f0c5df-d6e8-4ea8-8e95-936b336e62e8.png)
![image](https://user-images.githubusercontent.com/77124683/200061161-7981fe32-f92b-4ad4-afd2-e3e6078c84c7.png)
![image](https://user-images.githubusercontent.com/77124683/200061200-c580dbf7-2bd3-49f0-b456-a2aa3bfd8435.png)
