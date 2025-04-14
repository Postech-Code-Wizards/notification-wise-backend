#📲 **notification-wise-backend 📲**

### 🌟 **Visão Geral**

O **Serviço de Notificação** é responsável por enviar **mensagens** para os pacientes, utilizando **templates de notificação**. 
Ele pode enviar lembretes de consulta, notificações de cancelamento, e outros tipos de comunicação. As notificações podem ser enviadas através de diferentes canais, como **WhatsApp** e **email**. O serviço também registra os **logs de envio** para controle e monitoramento das tentativas de notificação.

Este serviço visa garantir que os pacientes recebam informações importantes de forma eficiente e sem falhas, proporcionando um **acompanhamento adequado** e garantindo que as comunicações sejam registradas e possam ser auditadas posteriormente.

---
### **Esquema de Banco de Dados 🗃️**
![Notification Service Database Schema](https://github.com/user-attachments/assets/dca704f6-75cb-4dda-a4b5-dbf7037f07ef)


---
### **Boas Práticas na Modelagem e Design**

#### **1. Uso de Templates Flexíveis**
- **Templates dinâmicos** são utilizados para permitir personalizações nas mensagens. Utilizando placeholders como `{patient_name}` e `{date}`, o sistema pode gerar notificações personalizadas e eficientes.
- Essa abordagem permite a criação de diversos tipos de mensagens (lembretes de consulta, cancelamento, entre outros), sem a necessidade de criar um novo código sempre que uma nova notificação for necessária.

#### **2. Registro de Logs**
- A tabela **`notification_logs`** garante que **todas as tentativas de envio** de notificações sejam registradas. Isso é **essencial para auditoria**, além de permitir que erros sejam **monitorados** e corrigidos.
- Armazenar o **status** da tentativa e a **mensagem de erro** permite identificar rapidamente **falhas de envio** e tomar ações corretivas.

#### **3. Controle de Status**
- A tabela **`notifications`** utiliza um campo de **status** para indicar se a notificação foi **enviada**, **pendente** ou **falhou**. Isso garante que o sistema tenha controle total sobre o estado de cada notificação.
- O status ajuda a **gerenciar o ciclo de vida das notificações**, permitindo que sejam feitas **tentativas de reenvio** caso a notificação falhe, e ajudando no **monitoramento da entrega**.

