var app = new Vue({
    el: '#app',
    data: {
        cards: [],
        cardColor: "",
        cardType: "",
    },
    created() {
    },
    methods: {
        creatCards() {
            console.log("Tarjeta creada")

           axios.post("/api/clients/current/cards", "cardType="+this.cardType+"&cardColor="+this.cardColor,"&deleteCard=false", {
            // axios.post('api/clients/current/cards', `cardColor${this.cardColor}&cardType=${this.cardType}`, {
                headers: { 'content-type': 'application/x-www-form-urlencoded' }
            })
            .then(response=>{
             window.location.href = "/web/Cards.html"})   
        },

    }  
})