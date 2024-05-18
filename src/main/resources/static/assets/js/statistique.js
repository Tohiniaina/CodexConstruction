
const label = ["Masculin","Feminin"];
const values1 = [50,50];


            const ctx = document.getElementById("myChart");
                                                                                                      
            const data = {
                labels: label,
                datasets: [
                    {
                        label: "# of Votes",
                        data: values1,
                        borderWidth: 1,
                    },
                ],
            };

            new Chart(ctx, {
                type: "pie",
                data: data,
                options: {
                    scales: {
                        y: {
                            
                            beginAtZero: true,
                        },
                    },
                },
            });


