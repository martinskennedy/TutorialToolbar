package com.example.tutorialtoolbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

/*
- CRIANDO A TOOLBAR
- Ir com o botão direito em layout e selecionar New > Layout Resource File. Vamos chamar o nosso
    arquivo de toolbar.
- Em Root element, vamos substituir o que quer que esteja escrito por
    androidx.appcompat.widget.Toolbar e clicar em Ok.

- IMPLEMENTANDO A TOOLBAR NA MAIN ACTIVITY
- Adicionar no Manifest a linha   android:theme="@style/Theme.AppCompat.DayNight.NoActionBar">
- Criar uma variável DENTRO DO onCreate(), para receber a Toolbar, atribuindo a ela a toolbar criada
    dentro da pasta de Resources e, em seguida, a usando como parâmetro para chamar o método.
- Incluir no layout da activity_main o layout da Toolbar <include layout="@layout/toolbar"/>

- CRIANDO O MENU
- Clicar com o botão direito na pasta res e New > Android Resource Directory. Vamos nomeá-lo como
    menu e alterar o seu tipo, também, para menu
- Agora, com o botão direito na recém-criada pasta menu, selecionamos New > Menu Resource File.
    Iremos nomear esse arquivo de main_menu. Se tudo der certo, você terá um novo arquivo
    main_menu.xml dentro da pasta app/res/menu, e poderá editá-lo tanto alterando seu código xml
    quanto no editor visual do Android Studio, como em qualquer arquivo xml.
    No item da busca, iremos incluir, a propriedade app:showAsAction="always", para que ele sempre
    apareça.
- Agora, vamos incluir o ícone de busca no lugar da palavra Busca, dentro do menu flutuante. Para
    isso, vamos clicar com o botão direito na pasta drawable - a pasta que guarda todos os arquivos
    de imagens do nosso projeto - e vamos em New > Vector Asset, que vai abrir a ferramenta
    Asset Studio, criada para auxiliar desenvolvedores a incluir ícones de uma maneira simples e
    prática. Para buscar o ícone que queremos, vamos clicar em Clip Art e, na barra de busca,
    pesquisar pela palavra-chave search, selecionando em seguida o ícone da lupa. Seu nome, por
    padrão, é ic_baseline_search_24. Vamos mantê-lo assim, mas você pode alterá-lo para um nome mais
    conciso (como ic_search, por exemplo) se quiser. Como a nossa Toolbar tem uma cor escura,
    também vamos mudar a cor do ícone para a cor branca, cujo código é #FFFFFF. Em seguida, é só
    clicar em Next > Finish. Nosso ícone está criado!
- De volta ao nosso arquivo menu.xml, basta incluir o atributo icon no nosso item de busca e definir
    o caminho para encontrá-lo dentro da pasta drawable

- INFLANDO O MENU NA MAIN ACTIVITY
- Temos o nosso menu criado, mas ele ainda não está aparecendo em nossa Main Activity, porque
    precisamos "inflá-lo" chamando a função onCreateOptionsMenu() e o método MenuInflater.inflate()

- IMPLEMENTANDO EVENTOS DE CLICK
    O método chamado pelo sistema quando o usuário clica em um dos botões do menu é o
    onOptionsItemSelected(), que passa o MenuItem selecionado como parâmetro. Para identificar o item
    dentro da nossa pasta de resources, chamamos o getItemId(). Essa função utiliza a sintaxe when e
    pode gerar alguma confusão por utilizar o operador de seta (->), assim como funções lambda. Por
    hora, basta entender que não se trata de uma função lambda, e que a condicional when exige a
    implementação de um else, que, nesse caso, é resolvido com a palavra-chave super.

CRIANDO UMA NOVA TELA
- Criar uma nova tela para inserir o botão de retorno, conhecido como up button, que retorna à
    atividade pai.
- Para criar uma nova tela, basta clicar com o botão direito no primeiro arquivo dentro da sua pasta
    java e New > Activity > Empty Activity. Irei nomeá-la de ChildActivity.
- A primeira coisa que vamos fazer nesta nova tela é implementar a Toolbar. Assim como fizemos com a
    MainActivity, iremos chamar o método setSupportActionBar()
- Também iremos torná-la uma filha da tela principal, para que ela herde os eventos de clique que
    acabamos de implementar. O editor do Android Studio automaticamente irá sinalizar
    (como faz com os imports pendentes) e pedir para tornar a MainActivity uma classe aberta
- E, no arquivo activity_child.xml, iremos inserir o <include layout="@layout/toolbar"/>. Também vou
    colocar um TextView simples, com um texto qualquer
 - Para navegar até essa tela, vamos voltar ao arquivo activity_main.xml e inserir um botão, cuja
    posição será abaixo do TextView, também através do Constraint Layout. Vou colocar um texto
    qualquer no botão ("hhhk") e fazer algumas alterações, também, no TextView. Irei mudar seu texto
    para "Olá Toolbar", aumentar seu tamanho de fonte e posicioná-lo um pouco acima do meio no eixo
    vertical da tela
- No código principal da MainActivity, vamos criar uma variável para armazenar o recurso do botão e
    implementar um ClickListener para navegar até a ChildActivity

- IMPLEMENTANDO O UP BUTTON
- Para criar o up button, precisamos voltar ao arquivo toolbar.xml, dentro da pasta app/res/layout,
    e inserir um navigationIcon.
- Primeiro, vamos criar o asset dentro da pasta app/res/drawable, como fizemos com o ícone de busca.
    Mas, desta vez, vamos selecionar o ícone arrow back, identificado pelo nome
    ic_baseline_arrow_back_24. Também usaremos a cor branca.
- Para selecioná-lo dentro do arquivo toolbar.xml, basta inserir o atributo
    app:navigationIcon="@drawable/ic_baseline_search_24".
- Como não queremos que ele apareça na MainActivity, o código implementado nela será
    supportActionBar?.setDisplayHomeAsUpEnabled(false). Já na ChildActivity, o código será o mesmo,
    mas com o parâmetro definido como true.
- Agora que ele está aparecendo em nossa segunda tela, precisamos configurá-lo para retornar à
    atividade pai. Para isso, vamos até o arquivo AndroidManifest.xml, dentro da pasta app/manifests,
    e, DENTRO DA TAG REFERENTE À NOSSA CHILD ACTIVITY, incluímos o código
    android:parentActivityName="com.example.tutorialtoolbar.MainActivity", esse código tem que estar
    de acordo com o package name do aplicativo.
 */

open class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val mIntent = Intent(this, ChildActivity::class.java)
            startActivity(mIntent)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.search -> {
            Toast.makeText(this, "Buscar item", Toast.LENGTH_LONG).show()
            true
        }
        R.id.func1 -> {
            Toast.makeText(this, "Funcionalidade 1", Toast.LENGTH_LONG).show()
            true
        }
        R.id.func2 -> {
            Toast.makeText(this, "Funcionalidade 2", Toast.LENGTH_LONG).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

}