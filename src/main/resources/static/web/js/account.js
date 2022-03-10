let app = new Vue ({
    el:'#app',
    data: {
        client:[],
        accounts:[],
        transactions:[],
        showTransaction:false,
        account:0,
        balance:0,
       
    },
    created(){
        this.loadData()
    },
    methods:{
        loadData(){
            axios.get(`http://localhost:8080/api/clients/current`)
            .then(response=>{
                this.client=response.data
                 this.accounts= response.data.account 
                 this.account= this.accounts.length
                //  console.log(this.account);

                // console.log(response.data);

            //  this.accounts.transactions.short((item_1,item_2)=>item_1.id) 
            //    console.log(this.accounts)
            }) 
            .catch((error)=>{
                console.log(error); //imprimir error si no funka
            })
        
    
        },
        getTransaction(id) {
            /* const urlParams = new URLSearchParams(window.location.search);
            const id = urlParams.get('id'); */
            axios.get(`http://localhost:8080/api/accounts/${id}`)
            .then(response=>{
                console.log("olaaa");
                this.client=response.data.transaction
                this.transactions= response.data.transaction
                this.accounts= response.data.account 
               console.log(response.data)
               for (let i = 0; i < this.transactions.length; i++) {
               this.balance += this.transactions[i].ammount;
            }
            this.balance=this.balance.toFixed(2)
            }) 
            showTransaction = true
        }, 
         getAccounts(type){
             axios.post("/api/clients/current/accounts",`accountType=${type}`)
             .then(response =>{
                    return window.location.href="./Accounts.html"}) 
              
         },

         deleteAccounts(id){ 
            axios.patch(`http://localhost:8080/api/clients/current/accounts/${id}`,`estado=true`)
            .then(response =>{
                window.location.reload()
            })
    },

    
}
})