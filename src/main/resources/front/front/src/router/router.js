import VueRouter from 'vue-router'

//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import qicheleixingList from '../pages/qicheleixing/list'
import qicheleixingDetail from '../pages/qicheleixing/detail'
import qicheleixingAdd from '../pages/qicheleixing/add'
import qichexinxiList from '../pages/qichexinxi/list'
import qichexinxiDetail from '../pages/qichexinxi/detail'
import qichexinxiAdd from '../pages/qichexinxi/add'
import qichezulinList from '../pages/qichezulin/list'
import qichezulinDetail from '../pages/qichezulin/detail'
import qichezulinAdd from '../pages/qichezulin/add'
import guihaixinxiList from '../pages/guihaixinxi/list'
import guihaixinxiDetail from '../pages/guihaixinxi/detail'
import guihaixinxiAdd from '../pages/guihaixinxi/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import aboutusList from '../pages/aboutus/list'
import aboutusDetail from '../pages/aboutus/detail'
import aboutusAdd from '../pages/aboutus/add'
import systemintroList from '../pages/systemintro/list'
import systemintroDetail from '../pages/systemintro/detail'
import systemintroAdd from '../pages/systemintro/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'qicheleixing',
					component: qicheleixingList
				},
				{
					path: 'qicheleixingDetail',
					component: qicheleixingDetail
				},
				{
					path: 'qicheleixingAdd',
					component: qicheleixingAdd
				},
				{
					path: 'qichexinxi',
					component: qichexinxiList
				},
				{
					path: 'qichexinxiDetail',
					component: qichexinxiDetail
				},
				{
					path: 'qichexinxiAdd',
					component: qichexinxiAdd
				},
				{
					path: 'qichezulin',
					component: qichezulinList
				},
				{
					path: 'qichezulinDetail',
					component: qichezulinDetail
				},
				{
					path: 'qichezulinAdd',
					component: qichezulinAdd
				},
				{
					path: 'guihaixinxi',
					component: guihaixinxiList
				},
				{
					path: 'guihaixinxiDetail',
					component: guihaixinxiDetail
				},
				{
					path: 'guihaixinxiAdd',
					component: guihaixinxiAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'aboutus',
					component: aboutusList
				},
				{
					path: 'aboutusDetail',
					component: aboutusDetail
				},
				{
					path: 'aboutusAdd',
					component: aboutusAdd
				},
				{
					path: 'systemintro',
					component: systemintroList
				},
				{
					path: 'systemintroDetail',
					component: systemintroDetail
				},
				{
					path: 'systemintroAdd',
					component: systemintroAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
