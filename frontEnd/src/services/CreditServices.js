import httpClient from "../http-commons.js";

const createCredit = (formData) => {
    return httpClient.post('/prestabanco/credit/save', formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    });
}

const updateCredit = (formData) => {
    return httpClient.put('/prestabanco/credit/Part1', formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    });
}

const evaluationCredit = (formData) => {
    return httpClient.put('/prestabanco/credit/EvaluarCredit', formData,
        {
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    });
}

const getAll = () => {
    return httpClient.get('/prestabanco/credit/getCredits');
}

const getCredit = (id) => {
    return httpClient.get(`/prestabanco/credit/getCredit/${id}`);
}

const updateStatus=(id)=>{
    return httpClient.put(`/prestabanco/credit/updateStatus/${id}`);
}

const searchCreditbyIdUser = (id) => {
    return httpClient.get(`/prestabanco/credit/getCreditByUserId/${id}`);
}
export default {
    createCredit,
    updateCredit,
    getAll,
    getCredit,
    updateStatus,
    evaluationCredit,
    searchCreditbyIdUser
};