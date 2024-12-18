//비동기 함수 사용 연습,
//axios,
//키워드, async, 함수의 선언부 앞에 사용하고,
//키워드, await, 비동기적으로 받아온 함수 앞에 사용, 반환 타임, Promise,

//비동기 함수의 핵심, 통보,
//비동기 함수가 동작하고, 무언가 알려주기,

async function getList({bno,page,size,goLast}){
    const result = await axios.get(`/replies/list/${bno}`,
        {params:{page,size}})
    // console.log(result)
    return result.data
}

