import httpClient from "../http-commons.js";

    const simulateCredit = (type, amount, term, rate) => {
        return httpClient.get(`/prestabanco/user/simulateCredit/${type}/${amount}/${term}/${rate}`);
    }

    const save = (formData) => {
        return httpClient.post('/prestabanco/user/save', formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        });
    }

    const validUser = (rut) => {
        return httpClient.get("/prestabanco/user/SearchUser", { params: { rut } });
    }



export default {
    simulateCredit,
    save,
    validUser
};

