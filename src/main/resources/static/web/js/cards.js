let app = new Vue ({
    el:'#app',
    data: {
        cards:[],
        debit:[],
        credit:[],
        clients:[],
        fecha:0,
        defeat:false,
    },

    created(){
        this.loadData()
    },
    methods:{
      loadData(){
        axios.get(`http://localhost:8080/api/clients/current`)
        .then(response=>{
            this.client=response.data
            this.cards= response.data.card 
            console.log(this.credit);
           console.log(this.debit);
           const hoy = new Date(Date.now())
					let fechaActual
					if (hoy.getMonth() + 1 < 10) {
						fechaActual =
							hoy.getFullYear().toString() +
							"-0" +
							(hoy.getMonth() + 1).toString() +
							"-" +
							hoy.getDate().toString()
					} else {
						fechaActual =
							hoy.getFullYear().toString() +
							"-" +
							(hoy.getMonth() + 1).toString() +
							"-" +
							hoy.getDate().toString()
					}
					this.fecha = fechaActual
					for (let i = 0; i < this.cards.length; i++) {
						if (this.cards[i].type == "DEBIT") {
							this.debit.push(this.cards[i])
						} else {
							this.credit.push(this.cards[i])
						}
					}

        })
        .catch((error)=>{
            console.log(error); 
        })
    },
    deleteCards(id){  
        axios.patch(`http://localhost:8080/api/clients/current/${id}`,`estado=true`)
        .then(response =>{
            window.location.reload()
        })
    }

    
}
    });





        
  