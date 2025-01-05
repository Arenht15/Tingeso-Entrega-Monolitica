import httpClient from "../http-commons.js";

const createSavingCapacity = (formData) => {
    return httpClient.post('/prestabanco/savingCapacity/save', formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    });
}

const getSavingCapacity = (id) => {
    return httpClient.get(`/prestabanco/savingCapacity/searchSavingCapacity/${id}`);
}

const updateSavingCapacity = (formData) => {
    return httpClient.put('/prestabanco/savingCapacity/update', formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    });
}

export default {
    getSavingCapacity,
    updateSavingCapacity,
    createSavingCapacity
}