//비동기 함수 사용 연습,
//axios,
//키워드, async, 함수의 선언부 앞에 사용하고,
//키워드, await, 비동기적으로 받아온 함수 앞에 사용, 반환 타임, Promise,

//비동기 함수의 핵심, 통보,
//비동기 함수가 동작하고, 무언가 알려주기,

// async function getList({bno,page,size,goLast}){
//     const result = await axios.get(`/replies/list/${bno}`,
//         {params:{page,size}})
//     // console.log(result)
//     return result.data
// }

//마지막 댓글 위치로 이동
async function getList({bno,page,size,goLast}){
    const result = await axios.get(`/replies/list/${bno}`,
        {params:{page,size}})
    // console.log(result)
    if(goLast){
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total/size))
        return getList({bno:bno,page:lastPage,size:size})
    }
    return result.data
}

//댓글 등록
async function addReply(replyObj){
    const response = await axios.post(`/replies/`,replyObj)
    return response.data
}

//댓글 조회
async function getReply(rno){
    const response = await axios.get(`/replies/${rno}`)
    return response.data
}

//댓글 수정
async function updateReply(replyObj){
    const response = await axios.put(`/replies/${replyObj.rno}`,replyObj)
    return response.data
}

//댓글 삭제
async function deleteReply(rno){
    const response = await axios.delete(`/replies/${rno}`)
    return response.data
}