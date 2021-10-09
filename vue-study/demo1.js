Vue.createApp({
    data() {
        return {
            author: {
                name: 'John Doe',
                books: [
                    'Vue 2 - Advanced Guide',
                    'Vue 3 - Basic Guide',
                    'Vue 4 - The Mystery'
                ]
            }
        }
    },
    methods: {
        // 计算属性的 getter
        publishedBooksMessage() {
            // `this` 指向 vm 实例
            return this.author.books.length > 0 ? 'Yes' : 'No'
        }
    }
}).mount('#computed-basics')




Vue.createApp({
    data() {
        return {
            isActive: true,
            hasError: true
        }
    }
}).mount('#demo1')

Vue.createApp({
    data(){
        return{
            syb: {
                color: 'red',
                fontsize: '30'
            }
        }
    }
}).mount('#demo2')

Vue.createApp({
    data(){
      return {
          jojo: 1
      }
    },
    methods:{
        say(){
            alert("Superhero")
            console.log("11");
        }
    }
}).mount('#demo3')

Vue.createApp({
    data(){
        return {
            msg: ''
        }
    }
}).mount("#demo4")