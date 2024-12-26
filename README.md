Pour pouvoir Envoyer Email depuis l'application : 
Il faut d'abord installer Node.js
Activez la verification a deux etapes dans son compte Google pour avoir mot de passe de l'application fournie par Google c'est a dire: 
Modifier cette du code pour mettre les donnees correspondants 
 const transporter = nodemailer.createTransport({
    service: 'gmail',
    auth: {
      user: 'yayac2298@gmail.com', // Remplace par ton email
      pass: 'kdgz dnye wbwa bblc', // Mot de passe de l'application (si validation en 2 étapes activée)
    },
  });

  Mettre l'adresse Ip de votre Ordinateur : 
 Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://100.101.56.21:3000")  // Remplacer par l'URL de votre serveur Node.js
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

  cd BackEnd_Node 
  node index.js ou nodemon index.js
Lancer l'application sous Android Studio pour envoyer l'email. 
  
