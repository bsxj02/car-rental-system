const base = {
    get() {
        return {
            url : "http://localhost:8080/springboot5e44amvc/",
            name: "springboot5e44amvc",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springboot5e44amvc/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "汽车租赁系统"
        } 
    }
}
export default base
