package com.example.carwalecovidtracker

import androidx.lifecycle.MutableLiveData
import com.example.carwalecovidtracker.pojo.CovidResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApiRepository {

//    var response = "{"Global":{"NewConfirmed":190531,"TotalConfirmed":9557043,"NewDeaths":0,"TotalDeaths":486079,"NewRecovered":398,"TotalRecovered":4630138},"Countries":[{"Country":"Afghanistan","CountryCode":"AF","Slug":"afghanistan","NewConfirmed":694,"TotalConfirmed":30175,"NewDeaths":0,"TotalDeaths":618,"NewRecovered":0,"TotalRecovered":9260,"Date":"2020-06-25T19:46:08Z"},{"Country":"Albania","CountryCode":"AL","Slug":"albania","NewConfirmed":145,"TotalConfirmed":2192,"NewDeaths":0,"TotalDeaths":45,"NewRecovered":0,"TotalRecovered":1195,"Date":"2020-06-25T19:46:08Z"},{"Country":"Algeria","CountryCode":"DZ","Slug":"algeria","NewConfirmed":172,"TotalConfirmed":12248,"NewDeaths":0,"TotalDeaths":861,"NewRecovered":0,"TotalRecovered":8674,"Date":"2020-06-25T19:46:08Z"},{"Country":"Andorra","CountryCode":"AD","Slug":"andorra","NewConfirmed":0,"TotalConfirmed":855,"NewDeaths":0,"TotalDeaths":52,"NewRecovered":0,"TotalRecovered":797,"Date":"2020-06-25T19:46:08Z"},{"Country":"Angola","CountryCode":"AO","Slug":"angola","NewConfirmed":8,"TotalConfirmed":197,"NewDeaths":0,"TotalDeaths":10,"NewRecovered":0,"TotalRecovered":77,"Date":"2020-06-25T19:46:08Z"},{"Country":"Antigua and Barbuda","CountryCode":"AG","Slug":"antigua-and-barbuda","NewConfirmed":39,"TotalConfirmed":65,"NewDeaths":0,"TotalDeaths":3,"NewRecovered":0,"TotalRecovered":22,"Date":"2020-06-25T19:46:08Z"},{"Country":"Argentina","CountryCode":"AR","Slug":"argentina","NewConfirmed":2648,"TotalConfirmed":49851,"NewDeaths":0,"TotalDeaths":1078,"NewRecovered":0,"TotalRecovered":13576,"Date":"2020-06-25T19:46:08Z"},{"Country":"Armenia","CountryCode":"AM","Slug":"armenia","NewConfirmed":1482,"TotalConfirmed":22488,"NewDeaths":0,"TotalDeaths":372,"NewRecovered":0,"TotalRecovered":10144,"Date":"2020-06-25T19:46:08Z"},{"Country":"Australia","CountryCode":"AU","Slug":"australia","NewConfirmed":37,"TotalConfirmed":7558,"NewDeaths":0,"TotalDeaths":103,"NewRecovered":0,"TotalRecovered":6924,"Date":"2020-06-25T19:46:08Z"},{"Country":"Austria","CountryCode":"AT","Slug":"austria","NewConfirmed":69,"TotalConfirmed":17477,"NewDeaths":0,"TotalDeaths":693,"NewRecovered":0,"TotalRecovered":16261,"Date":"2020-06-25T19:46:08Z"},{"Country":"Azerbaijan","CountryCode":"AZ","Slug":"azerbaijan","NewConfirmed":590,"TotalConfirmed":14305,"NewDeaths":0,"TotalDeaths":167,"NewRecovered":0,"TotalRecovered":7503,"Date":"2020-06-25T19:46:08Z"},{"Country":"Bahamas","CountryCode":"BS","Slug":"bahamas","NewConfirmed":0,"TotalConfirmed":104,"NewDeaths":0,"TotalDeaths":11,"NewRecovered":0,"TotalRecovered":83,"Date":"2020-06-25T19:46:08Z"},{"Country":"Bahrain","CountryCode":"BH","Slug":"bahrain","NewConfirmed":508,"TotalConfirmed":23570,"NewDeaths":0,"TotalDeaths":67,"NewRecovered":0,"TotalRecovered":17450,"Date":"2020-06-25T19:46:08Z"},{"Country":"Bangladesh","CountryCode":"BD","Slug":"bangladesh","NewConfirmed":7408,"TotalConfirmed":126606,"NewDeaths":0,"TotalDeaths":1545,"NewRecovered":0,"TotalRecovered":47635,"Date":"2020-06-25T19:46:08Z"},{"Country":"Barbados","CountryCode":"BB","Slug":"barbados","NewConfirmed":0,"TotalConfirmed":97,"NewDeaths":0,"TotalDeaths":7,"NewRecovered":0,"TotalRecovered":85,"Date":"2020-06-25T19:46:08Z"},{"Country":"Belarus","CountryCode":"BY","Slug":"belarus","NewConfirmed":895,"TotalConfirmed":60382,"NewDeaths":0,"TotalDeaths":357,"NewRecovered":0,"TotalRecovered":38688,"Date":"2020-06-25T19:46:08Z"},{"Country":"Belgium","CountryCode":"BE","Slug":"belgium","NewConfirmed":197,"TotalConfirmed":61007,"NewDeaths":0,"TotalDeaths":9713,"NewRecovered":0,"TotalRecovered":16771,"Date":"2020-06-25T19:46:08Z"},{"Country":"Belize","CountryCode":"BZ","Slug":"belize","NewConfirmed":0,"TotalConfirmed":23,"NewDeaths":0,"TotalDeaths":2,"NewRecovered":0,"TotalRecovered":17,"Date":"2020-06-25T19:46:08Z"},{"Country":"Benin","CountryCode":"BJ","Slug":"benin","NewConfirmed":167,"TotalConfirmed":1017,"NewDeaths":0,"TotalDeaths":13,"NewRecovered":0,"TotalRecovered":272,"Date":"2020-06-25T19:46:08Z"},{"Country":"Bhutan","CountryCode":"BT","Slug":"bhutan","NewConfirmed":0,"TotalConfirmed":70,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":34,"Date":"2020-06-25T19:46:08Z"},{"Country":"Bolivia","CountryCode":"BO","Slug":"bolivia","NewConfirmed":1098,"TotalConfirmed":27487,"NewDeaths":0,"TotalDeaths":846,"NewRecovered":0,"TotalRecovered":6300,"Date":"2020-06-25T19:46:08Z"},{"Country":"Bosnia and Herzegovina","CountryCode":"BA","Slug":"bosnia-and-herzegovina","NewConfirmed":88,"TotalConfirmed":3676,"NewDeaths":0,"TotalDeaths":172,"NewRecovered":0,"TotalRecovered":2285,"Date":"2020-06-25T19:46:08Z"},{"Country":"Botswana","CountryCode":"BW","Slug":"botswana","NewConfirmed":3,"TotalConfirmed":92,"NewDeaths":0,"TotalDeaths":1,"NewRecovered":0,"TotalRecovered":25,"Date":"2020-06-25T19:46:08Z"},{"Country":"Brazil","CountryCode":"BR","Slug":"brazil","NewConfirmed":42725,"TotalConfirmed":1188631,"NewDeaths":0,"TotalDeaths":52645,"NewRecovered":0,"TotalRecovered":627963,"Date":"2020-06-25T19:46:08Z"},{"Country":"Brunei Darussalam","CountryCode":"BN","Slug":"brunei","NewConfirmed":0,"TotalConfirmed":141,"NewDeaths":0,"TotalDeaths":3,"NewRecovered":0,"TotalRecovered":138,"Date":"2020-06-25T19:46:08Z"},{"Country":"Bulgaria","CountryCode":"BG","Slug":"bulgaria","NewConfirmed":128,"TotalConfirmed":4242,"NewDeaths":0,"TotalDeaths":208,"NewRecovered":0,"TotalRecovered":2217,"Date":"2020-06-25T19:46:08Z"},{"Country":"Burkina Faso","CountryCode":"BF","Slug":"burkina-faso","NewConfirmed":27,"TotalConfirmed":934,"NewDeaths":0,"TotalDeaths":53,"NewRecovered":0,"TotalRecovered":823,"Date":"2020-06-25T19:46:08Z"},{"Country":"Burundi","CountryCode":"BI","Slug":"burundi","NewConfirmed":0,"TotalConfirmed":144,"NewDeaths":0,"TotalDeaths":1,"NewRecovered":0,"TotalRecovered":93,"Date":"2020-06-25T19:46:08Z"},{"Country":"Cambodia","CountryCode":"KH","Slug":"cambodia","NewConfirmed":0,"TotalConfirmed":130,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":127,"Date":"2020-06-25T19:46:08Z"},{"Country":"Cameroon","CountryCode":"CM","Slug":"cameroon","NewConfirmed":322,"TotalConfirmed":12592,"NewDeaths":0,"TotalDeaths":313,"NewRecovered":0,"TotalRecovered":7774,"Date":"2020-06-25T19:46:08Z"},{"Country":"Canada","CountryCode":"CA","Slug":"canada","NewConfirmed":640,"TotalConfirmed":208174,"NewDeaths":0,"TotalDeaths":17022,"NewRecovered":398,"TotalRecovered":66533,"Date":"2020-06-25T19:46:08Z"},{"Country":"Cape Verde","CountryCode":"CV","Slug":"cape-verde","NewConfirmed":17,"TotalConfirmed":999,"NewDeaths":0,"TotalDeaths":8,"NewRecovered":0,"TotalRecovered":426,"Date":"2020-06-25T19:46:08Z"},{"Country":"Central African Republic","CountryCode":"CF","Slug":"central-african-republic","NewConfirmed":48,"TotalConfirmed":3099,"NewDeaths":0,"TotalDeaths":37,"NewRecovered":0,"TotalRecovered":522,"Date":"2020-06-25T19:46:08Z"},{"Country":"Chad","CountryCode":"TD","Slug":"chad","NewConfirmed":0,"TotalConfirmed":860,"NewDeaths":0,"TotalDeaths":74,"NewRecovered":0,"TotalRecovered":757,"Date":"2020-06-25T19:46:08Z"},{"Country":"Chile","CountryCode":"CL","Slug":"chile","NewConfirmed":3649,"TotalConfirmed":254416,"NewDeaths":0,"TotalDeaths":4505,"NewRecovered":0,"TotalRecovered":210570,"Date":"2020-06-25T19:46:08Z"},{"Country":"China","CountryCode":"CN","Slug":"china","NewConfirmed":20,"TotalConfirmed":84673,"NewDeaths":0,"TotalDeaths":4640,"NewRecovered":0,"TotalRecovered":79555,"Date":"2020-06-25T19:46:08Z"},{"Country":"Colombia","CountryCode":"CO","Slug":"colombia","NewConfirmed":0,"TotalConfirmed":73760,"NewDeaths":0,"TotalDeaths":2524,"NewRecovered":0,"TotalRecovered":30517,"Date":"2020-06-25T19:46:08Z"},{"Country":"Comoros","CountryCode":"KM","Slug":"comoros","NewConfirmed":0,"TotalConfirmed":265,"NewDeaths":0,"TotalDeaths":7,"NewRecovered":0,"TotalRecovered":159,"Date":"2020-06-25T19:46:08Z"},{"Country":"Congo (Brazzaville)","CountryCode":"CG","Slug":"congo-brazzaville","NewConfirmed":0,"TotalConfirmed":1087,"NewDeaths":0,"TotalDeaths":37,"NewRecovered":0,"TotalRecovered":456,"Date":"2020-06-25T19:46:08Z"},{"Country":"Congo (Kinshasa)","CountryCode":"CD","Slug":"congo-kinshasa","NewConfirmed":186,"TotalConfirmed":6213,"NewDeaths":0,"TotalDeaths":135,"NewRecovered":0,"TotalRecovered":861,"Date":"2020-06-25T19:46:08Z"},{"Country":"Costa Rica","CountryCode":"CR","Slug":"costa-rica","NewConfirmed":147,"TotalConfirmed":2515,"NewDeaths":0,"TotalDeaths":12,"NewRecovered":0,"TotalRecovered":1129,"Date":"2020-06-25T19:46:08Z"},{"Country":"Croatia","CountryCode":"HR","Slug":"croatia","NewConfirmed":22,"TotalConfirmed":2388,"NewDeaths":0,"TotalDeaths":107,"NewRecovered":0,"TotalRecovered":2142,"Date":"2020-06-25T19:46:08Z"},{"Country":"Cuba","CountryCode":"CU","Slug":"cuba","NewConfirmed":1,"TotalConfirmed":2319,"NewDeaths":0,"TotalDeaths":85,"NewRecovered":0,"TotalRecovered":2123,"Date":"2020-06-25T19:46:08Z"},{"Country":"Cyprus","CountryCode":"CY","Slug":"cyprus","NewConfirmed":1,"TotalConfirmed":991,"NewDeaths":0,"TotalDeaths":19,"NewRecovered":0,"TotalRecovered":824,"Date":"2020-06-25T19:46:08Z"},{"Country":"Czech Republic","CountryCode":"CZ","Slug":"czech-republic","NewConfirmed":130,"TotalConfirmed":10780,"NewDeaths":0,"TotalDeaths":339,"NewRecovered":0,"TotalRecovered":7555,"Date":"2020-06-25T19:46:08Z"},{"Country":"Côte d'Ivoire","CountryCode":"CI","Slug":"cote-divoire","NewConfirmed":260,"TotalConfirmed":8164,"NewDeaths":0,"TotalDeaths":58,"NewRecovered":0,"TotalRecovered":3182,"Date":"2020-06-25T19:46:08Z"},{"Country":"Denmark","CountryCode":"DK","Slug":"denmark","NewConfirmed":54,"TotalConfirmed":12815,"NewDeaths":0,"TotalDeaths":603,"NewRecovered":0,"TotalRecovered":11593,"Date":"2020-06-25T19:46:08Z"},{"Country":"Djibouti","CountryCode":"DJ","Slug":"djibouti","NewConfirmed":13,"TotalConfirmed":4630,"NewDeaths":0,"TotalDeaths":49,"NewRecovered":0,"TotalRecovered":3989,"Date":"2020-06-25T19:46:08Z"},{"Country":"Dominica","CountryCode":"DM","Slug":"dominica","NewConfirmed":0,"TotalConfirmed":18,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":18,"Date":"2020-06-25T19:46:08Z"},{"Country":"Dominican Republic","CountryCode":"DO","Slug":"dominican-republic","NewConfirmed":695,"TotalConfirmed":28631,"NewDeaths":0,"TotalDeaths":675,"NewRecovered":0,"TotalRecovered":15551,"Date":"2020-06-25T19:46:08Z"},{"Country":"Ecuador","CountryCode":"EC","Slug":"ecuador","NewConfirmed":0,"TotalConfirmed":51643,"NewDeaths":0,"TotalDeaths":4274,"NewRecovered":0,"TotalRecovered":24991,"Date":"2020-06-25T19:46:08Z"},{"Country":"Egypt","CountryCode":"EG","Slug":"egypt","NewConfirmed":1420,"TotalConfirmed":59561,"NewDeaths":0,"TotalDeaths":2365,"NewRecovered":0,"TotalRecovered":15535,"Date":"2020-06-25T19:46:08Z"},{"Country":"El Salvador","CountryCode":"SV","Slug":"el-salvador","NewConfirmed":363,"TotalConfirmed":5336,"NewDeaths":0,"TotalDeaths":113,"NewRecovered":0,"TotalRecovered":2814,"Date":"2020-06-25T19:46:08Z"},{"Country":"Equatorial Guinea","CountryCode":"GQ","Slug":"equatorial-guinea","NewConfirmed":0,"TotalConfirmed":1664,"NewDeaths":0,"TotalDeaths":32,"NewRecovered":0,"TotalRecovered":515,"Date":"2020-06-25T19:46:08Z"},{"Country":"Eritrea","CountryCode":"ER","Slug":"eritrea","NewConfirmed":1,"TotalConfirmed":144,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":39,"Date":"2020-06-25T19:46:08Z"},{"Country":"Estonia","CountryCode":"EE","Slug":"estonia","NewConfirmed":2,"TotalConfirmed":1984,"NewDeaths":0,"TotalDeaths":69,"NewRecovered":0,"TotalRecovered":1771,"Date":"2020-06-25T19:46:08Z"},{"Country":"Ethiopia","CountryCode":"ET","Slug":"ethiopia","NewConfirmed":186,"TotalConfirmed":5034,"NewDeaths":0,"TotalDeaths":75,"NewRecovered":0,"TotalRecovered":1412,"Date":"2020-06-25T19:46:08Z"},{"Country":"Fiji","CountryCode":"FJ","Slug":"fiji","NewConfirmed":0,"TotalConfirmed":18,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":18,"Date":"2020-06-25T19:46:08Z"},{"Country":"Finland","CountryCode":"FI","Slug":"finland","NewConfirmed":17,"TotalConfirmed":7172,"NewDeaths":0,"TotalDeaths":327,"NewRecovered":0,"TotalRecovered":6400,"Date":"2020-06-25T19:46:08Z"},{"Country":"France","CountryCode":"FR","Slug":"france","NewConfirmed":81,"TotalConfirmed":197885,"NewDeaths":0,"TotalDeaths":29723,"NewRecovered":0,"TotalRecovered":74995,"Date":"2020-06-25T19:46:08Z"},{"Country":"Gabon","CountryCode":"GA","Slug":"gabon","NewConfirmed":107,"TotalConfirmed":4956,"NewDeaths":0,"TotalDeaths":39,"NewRecovered":0,"TotalRecovered":2107,"Date":"2020-06-25T19:46:08Z"},{"Country":"Gambia","CountryCode":"GM","Slug":"gambia","NewConfirmed":0,"TotalConfirmed":42,"NewDeaths":0,"TotalDeaths":2,"NewRecovered":0,"TotalRecovered":26,"Date":"2020-06-25T19:46:08Z"},{"Country":"Georgia","CountryCode":"GE","Slug":"georgia","NewConfirmed":6,"TotalConfirmed":917,"NewDeaths":0,"TotalDeaths":14,"NewRecovered":0,"TotalRecovered":768,"Date":"2020-06-25T19:46:08Z"},{"Country":"Germany","CountryCode":"DE","Slug":"germany","NewConfirmed":811,"TotalConfirmed":193291,"NewDeaths":0,"TotalDeaths":8914,"NewRecovered":0,"TotalRecovered":175825,"Date":"2020-06-25T19:46:08Z"},{"Country":"Ghana","CountryCode":"GH","Slug":"ghana","NewConfirmed":445,"TotalConfirmed":15013,"NewDeaths":0,"TotalDeaths":95,"NewRecovered":0,"TotalRecovered":10907,"Date":"2020-06-25T19:46:08Z"},{"Country":"Greece","CountryCode":"GR","Slug":"greece","NewConfirmed":8,"TotalConfirmed":3310,"NewDeaths":0,"TotalDeaths":190,"NewRecovered":0,"TotalRecovered":1374,"Date":"2020-06-25T19:46:08Z"},{"Country":"Grenada","CountryCode":"GD","Slug":"grenada","NewConfirmed":0,"TotalConfirmed":23,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":23,"Date":"2020-06-25T19:46:08Z"},{"Country":"Guatemala","CountryCode":"GT","Slug":"guatemala","NewConfirmed":279,"TotalConfirmed":14819,"NewDeaths":0,"TotalDeaths":582,"NewRecovered":0,"TotalRecovered":2897,"Date":"2020-06-25T19:46:08Z"},{"Country":"Guinea","CountryCode":"GN","Slug":"guinea","NewConfirmed":134,"TotalConfirmed":5174,"NewDeaths":0,"TotalDeaths":28,"NewRecovered":0,"TotalRecovered":3685,"Date":"2020-06-25T19:46:08Z"},{"Country":"Guinea-Bissau","CountryCode":"GW","Slug":"guinea-bissau","NewConfirmed":0,"TotalConfirmed":1556,"NewDeaths":0,"TotalDeaths":19,"NewRecovered":0,"TotalRecovered":191,"Date":"2020-06-25T19:46:08Z"},{"Country":"Guyana","CountryCode":"GY","Slug":"guyana","NewConfirmed":3,"TotalConfirmed":209,"NewDeaths":0,"TotalDeaths":12,"NewRecovered":0,"TotalRecovered":107,"Date":"2020-06-25T19:46:08Z"},{"Country":"Haiti","CountryCode":"HT","Slug":"haiti","NewConfirmed":105,"TotalConfirmed":5429,"NewDeaths":0,"TotalDeaths":89,"NewRecovered":0,"TotalRecovered":436,"Date":"2020-06-25T19:46:08Z"},{"Country":"Holy See (Vatican City State)","CountryCode":"VA","Slug":"holy-see-vatican-city-state","NewConfirmed":0,"TotalConfirmed":12,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":12,"Date":"2020-06-25T19:46:08Z"},{"Country":"Honduras","CountryCode":"HN","Slug":"honduras","NewConfirmed":628,"TotalConfirmed":14571,"NewDeaths":0,"TotalDeaths":405,"NewRecovered":0,"TotalRecovered":1461,"Date":"2020-06-25T19:46:08Z"},{"Country":"Hungary","CountryCode":"HU","Slug":"hungary","NewConfirmed":16,"TotalConfirmed":4123,"NewDeaths":0,"TotalDeaths":573,"NewRecovered":0,"TotalRecovered":2600,"Date":"2020-06-25T19:46:08Z"},{"Country":"Iceland","CountryCode":"IS","Slug":"iceland","NewConfirmed":0,"TotalConfirmed":1824,"NewDeaths":0,"TotalDeaths":10,"NewRecovered":0,"TotalRecovered":1806,"Date":"2020-06-25T19:46:08Z"},{"Country":"India","CountryCode":"IN","Slug":"india","NewConfirmed":16922,"TotalConfirmed":473105,"NewDeaths":0,"TotalDeaths":14476,"NewRecovered":0,"TotalRecovered":258685,"Date":"2020-06-25T19:46:08Z"},{"Country":"Indonesia","CountryCode":"ID","Slug":"indonesia","NewConfirmed":2291,"TotalConfirmed":50187,"NewDeaths":0,"TotalDeaths":2535,"NewRecovered":0,"TotalRecovered":19241,"Date":"2020-06-25T19:46:08Z"},{"Country":"Iran, Islamic Republic of","CountryCode":"IR","Slug":"iran","NewConfirmed":5126,"TotalConfirmed":215096,"NewDeaths":0,"TotalDeaths":9863,"NewRecovered":0,"TotalRecovered":169160,"Date":"2020-06-25T19:46:08Z"},{"Country":"Iraq","CountryCode":"IQ","Slug":"iraq","NewConfirmed":2200,"TotalConfirmed":36702,"NewDeaths":0,"TotalDeaths":1251,"NewRecovered":0,"TotalRecovered":15753,"Date":"2020-06-25T19:46:08Z"},{"Country":"Ireland","CountryCode":"IE","Slug":"ireland","NewConfirmed":5,"TotalConfirmed":25396,"NewDeaths":0,"TotalDeaths":1720,"NewRecovered":0,"TotalRecovered":22698,"Date":"2020-06-25T19:46:08Z"},{"Country":"Israel","CountryCode":"IL","Slug":"israel","NewConfirmed":627,"TotalConfirmed":22139,"NewDeaths":0,"TotalDeaths":308,"NewRecovered":0,"TotalRecovered":15869,"Date":"2020-06-25T19:46:08Z"},{"Country":"Italy","CountryCode":"IT","Slug":"italy","NewConfirmed":577,"TotalConfirmed":239410,"NewDeaths":0,"TotalDeaths":34675,"NewRecovered":0,"TotalRecovered":184585,"Date":"2020-06-25T19:46:08Z"},{"Country":"Jamaica","CountryCode":"JM","Slug":"jamaica","NewConfirmed":8,"TotalConfirmed":678,"NewDeaths":0,"TotalDeaths":10,"NewRecovered":0,"TotalRecovered":518,"Date":"2020-06-25T19:46:08Z"},{"Country":"Japan","CountryCode":"JP","Slug":"japan","NewConfirmed":134,"TotalConfirmed":18013,"NewDeaths":0,"TotalDeaths":965,"NewRecovered":0,"TotalRecovered":16096,"Date":"2020-06-25T19:46:08Z"},{"Country":"Jordan","CountryCode":"JO","Slug":"jordan","NewConfirmed":24,"TotalConfirmed":1071,"NewDeaths":0,"TotalDeaths":9,"NewRecovered":0,"TotalRecovered":772,"Date":"2020-06-25T19:46:08Z"},{"Country":"Kazakhstan","CountryCode":"KZ","Slug":"kazakhstan","NewConfirmed":520,"TotalConfirmed":19285,"NewDeaths":0,"TotalDeaths":134,"NewRecovered":0,"TotalRecovered":11514,"Date":"2020-06-25T19:46:08Z"},{"Country":"Kenya","CountryCode":"KE","Slug":"kenya","NewConfirmed":254,"TotalConfirmed":5206,"NewDeaths":0,"TotalDeaths":128,"NewRecovered":0,"TotalRecovered":1782,"Date":"2020-06-25T19:46:08Z"},{"Country":"Korea (South)","CountryCode":"KR","Slug":"korea-south","NewConfirmed":28,"TotalConfirmed":12563,"NewDeaths":0,"TotalDeaths":281,"NewRecovered":0,"TotalRecovered":10930,"Date":"2020-06-25T19:46:08Z"},{"Country":"Kuwait","CountryCode":"KW","Slug":"kuwait","NewConfirmed":846,"TotalConfirmed":41879,"NewDeaths":0,"TotalDeaths":334,"NewRecovered":0,"TotalRecovered":32304,"Date":"2020-06-25T19:46:08Z"},{"Country":"Kyrgyzstan","CountryCode":"KG","Slug":"kyrgyzstan","NewConfirmed":228,"TotalConfirmed":3954,"NewDeaths":0,"TotalDeaths":42,"NewRecovered":0,"TotalRecovered":2082,"Date":"2020-06-25T19:46:08Z"},{"Country":"Lao PDR","CountryCode":"LA","Slug":"lao-pdr","NewConfirmed":0,"TotalConfirmed":19,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":19,"Date":"2020-06-25T19:46:08Z"},{"Country":"Latvia","CountryCode":"LV","Slug":"latvia","NewConfirmed":0,"TotalConfirmed":1111,"NewDeaths":0,"TotalDeaths":30,"NewRecovered":0,"TotalRecovered":903,"Date":"2020-06-25T19:46:08Z"},{"Country":"Lebanon","CountryCode":"LB","Slug":"lebanon","NewConfirmed":22,"TotalConfirmed":1644,"NewDeaths":0,"TotalDeaths":32,"NewRecovered":0,"TotalRecovered":1098,"Date":"2020-06-25T19:46:08Z"},{"Country":"Lesotho","CountryCode":"LS","Slug":"lesotho","NewConfirmed":0,"TotalConfirmed":17,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":2,"Date":"2020-06-25T19:46:08Z"},{"Country":"Liberia","CountryCode":"LR","Slug":"liberia","NewConfirmed":10,"TotalConfirmed":662,"NewDeaths":0,"TotalDeaths":34,"NewRecovered":0,"TotalRecovered":270,"Date":"2020-06-25T19:46:08Z"},{"Country":"Libya","CountryCode":"LY","Slug":"libya","NewConfirmed":31,"TotalConfirmed":670,"NewDeaths":0,"TotalDeaths":17,"NewRecovered":0,"TotalRecovered":132,"Date":"2020-06-25T19:46:08Z"},{"Country":"Liechtenstein","CountryCode":"LI","Slug":"liechtenstein","NewConfirmed":0,"TotalConfirmed":86,"NewDeaths":0,"TotalDeaths":2,"NewRecovered":0,"TotalRecovered":72,"Date":"2020-06-25T19:46:08Z"},{"Country":"Lithuania","CountryCode":"LT","Slug":"lithuania","NewConfirmed":3,"TotalConfirmed":1806,"NewDeaths":0,"TotalDeaths":77,"NewRecovered":0,"TotalRecovered":1483,"Date":"2020-06-25T19:46:08Z"},{"Country":"Luxembourg","CountryCode":"LU","Slug":"luxembourg","NewConfirmed":7,"TotalConfirmed":4140,"NewDeaths":0,"TotalDeaths":110,"NewRecovered":0,"TotalRecovered":3959,"Date":"2020-06-25T19:46:08Z"},{"Country":"Macedonia, Republic of","CountryCode":"MK","Slug":"macedonia","NewConfirmed":134,"TotalConfirmed":5445,"NewDeaths":0,"TotalDeaths":251,"NewRecovered":0,"TotalRecovered":2048,"Date":"2020-06-25T19:46:08Z"},{"Country":"Madagascar","CountryCode":"MG","Slug":"madagascar","NewConfirmed":105,"TotalConfirmed":1829,"NewDeaths":0,"TotalDeaths":15,"NewRecovered":0,"TotalRecovered":732,"Date":"2020-06-25T19:46:08Z"},{"Country":"Malawi","CountryCode":"MW","Slug":"malawi","NewConfirmed":138,"TotalConfirmed":941,"NewDeaths":0,"TotalDeaths":11,"NewRecovered":0,"TotalRecovered":258,"Date":"2020-06-25T19:46:08Z"},{"Country":"Malaysia","CountryCode":"MY","Slug":"malaysia","NewConfirmed":10,"TotalConfirmed":8600,"NewDeaths":0,"TotalDeaths":121,"NewRecovered":0,"TotalRecovered":8186,"Date":"2020-06-25T19:46:08Z"},{"Country":"Maldives","CountryCode":"MV","Slug":"maldives","NewConfirmed":23,"TotalConfirmed":2261,"NewDeaths":0,"TotalDeaths":8,"NewRecovered":0,"TotalRecovered":1813,"Date":"2020-06-25T19:46:08Z"},{"Country":"Mali","CountryCode":"ML","Slug":"mali","NewConfirmed":27,"TotalConfirmed":2005,"NewDeaths":0,"TotalDeaths":111,"NewRecovered":0,"TotalRecovered":1302,"Date":"2020-06-25T19:46:08Z"},{"Country":"Malta","CountryCode":"MT","Slug":"malta","NewConfirmed":3,"TotalConfirmed":668,"NewDeaths":0,"TotalDeaths":9,"NewRecovered":0,"TotalRecovered":618,"Date":"2020-06-25T19:46:08Z"},{"Country":"Mauritania","CountryCode":"MR","Slug":"mauritania","NewConfirmed":227,"TotalConfirmed":3519,"NewDeaths":0,"TotalDeaths":114,"NewRecovered":0,"TotalRecovered":963,"Date":"2020-06-25T19:46:08Z"},{"Country":"Mauritius","CountryCode":"MU","Slug":"mauritius","NewConfirmed":1,"TotalConfirmed":341,"NewDeaths":0,"TotalDeaths":10,"NewRecovered":0,"TotalRecovered":326,"Date":"2020-06-25T19:46:08Z"},{"Country":"Mexico","CountryCode":"MX","Slug":"mexico","NewConfirmed":5437,"TotalConfirmed":196847,"NewDeaths":0,"TotalDeaths":23377,"NewRecovered":0,"TotalRecovered":143646,"Date":"2020-06-25T19:46:08Z"},{"Country":"Moldova","CountryCode":"MD","Slug":"moldova","NewConfirmed":364,"TotalConfirmed":15078,"NewDeaths":0,"TotalDeaths":490,"NewRecovered":0,"TotalRecovered":8212,"Date":"2020-06-25T19:46:08Z"},{"Country":"Monaco","CountryCode":"MC","Slug":"monaco","NewConfirmed":1,"TotalConfirmed":102,"NewDeaths":0,"TotalDeaths":4,"NewRecovered":0,"TotalRecovered":95,"Date":"2020-06-25T19:46:08Z"},{"Country":"Mongolia","CountryCode":"MN","Slug":"mongolia","NewConfirmed":1,"TotalConfirmed":216,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":168,"Date":"2020-06-25T19:46:08Z"},{"Country":"Montenegro","CountryCode":"ME","Slug":"montenegro","NewConfirmed":11,"TotalConfirmed":389,"NewDeaths":0,"TotalDeaths":9,"NewRecovered":0,"TotalRecovered":315,"Date":"2020-06-25T19:46:08Z"},{"Country":"Morocco","CountryCode":"MA","Slug":"morocco","NewConfirmed":935,"TotalConfirmed":11279,"NewDeaths":0,"TotalDeaths":214,"NewRecovered":0,"TotalRecovered":8407,"Date":"2020-06-25T19:46:08Z"},{"Country":"Mozambique","CountryCode":"MZ","Slug":"mozambique","NewConfirmed":5,"TotalConfirmed":762,"NewDeaths":0,"TotalDeaths":5,"NewRecovered":0,"TotalRecovered":206,"Date":"2020-06-25T19:46:08Z"},{"Country":"Myanmar","CountryCode":"MM","Slug":"myanmar","NewConfirmed":1,"TotalConfirmed":293,"NewDeaths":0,"TotalDeaths":6,"NewRecovered":0,"TotalRecovered":204,"Date":"2020-06-25T19:46:08Z"},{"Country":"Namibia","CountryCode":"NA","Slug":"namibia","NewConfirmed":18,"TotalConfirmed":90,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":21,"Date":"2020-06-25T19:46:08Z"},{"Country":"Nepal","CountryCode":"NP","Slug":"nepal","NewConfirmed":1063,"TotalConfirmed":11162,"NewDeaths":0,"TotalDeaths":24,"NewRecovered":0,"TotalRecovered":2224,"Date":"2020-06-25T19:46:08Z"},{"Country":"Netherlands","CountryCode":"NL","Slug":"netherlands","NewConfirmed":82,"TotalConfirmed":50012,"NewDeaths":0,"TotalDeaths":6114,"NewRecovered":0,"TotalRecovered":186,"Date":"2020-06-25T19:46:08Z"},{"Country":"New Zealand","CountryCode":"NZ","Slug":"new-zealand","NewConfirmed":3,"TotalConfirmed":1519,"NewDeaths":0,"TotalDeaths":22,"NewRecovered":0,"TotalRecovered":1483,"Date":"2020-06-25T19:46:08Z"},{"Country":"Nicaragua","CountryCode":"NI","Slug":"nicaragua","NewConfirmed":0,"TotalConfirmed":2170,"NewDeaths":0,"TotalDeaths":74,"NewRecovered":0,"TotalRecovered":1238,"Date":"2020-06-25T19:46:08Z"},{"Country":"Niger","CountryCode":"NE","Slug":"niger","NewConfirmed":0,"TotalConfirmed":1051,"NewDeaths":0,"TotalDeaths":67,"NewRecovered":0,"TotalRecovered":913,"Date":"2020-06-25T19:46:08Z"},{"Country":"Nigeria","CountryCode":"NG","Slug":"nigeria","NewConfirmed":649,"TotalConfirmed":22020,"NewDeaths":0,"TotalDeaths":533,"NewRecovered":0,"TotalRecovered":7338,"Date":"2020-06-25T19:46:08Z"},{"Country":"Norway","CountryCode":"NO","Slug":"norway","NewConfirmed":16,"TotalConfirmed":8788,"NewDeaths":0,"TotalDeaths":248,"NewRecovered":0,"TotalRecovered":8138,"Date":"2020-06-25T19:46:08Z"},{"Country":"Oman","CountryCode":"OM","Slug":"oman","NewConfirmed":2508,"TotalConfirmed":34902,"NewDeaths":0,"TotalDeaths":140,"NewRecovered":0,"TotalRecovered":17279,"Date":"2020-06-25T19:46:08Z"},{"Country":"Pakistan","CountryCode":"PK","Slug":"pakistan","NewConfirmed":4044,"TotalConfirmed":192970,"NewDeaths":0,"TotalDeaths":3755,"NewRecovered":0,"TotalRecovered":77754,"Date":"2020-06-25T19:46:08Z"},{"Country":"Palestinian Territory","CountryCode":"PS","Slug":"palestine","NewConfirmed":193,"TotalConfirmed":1362,"NewDeaths":0,"TotalDeaths":3,"NewRecovered":0,"TotalRecovered":442,"Date":"2020-06-25T19:46:08Z"},{"Country":"Panama","CountryCode":"PA","Slug":"panama","NewConfirmed":716,"TotalConfirmed":28030,"NewDeaths":0,"TotalDeaths":536,"NewRecovered":0,"TotalRecovered":14694,"Date":"2020-06-25T19:46:08Z"},{"Country":"Papua New Guinea","CountryCode":"PG","Slug":"papua-new-guinea","NewConfirmed":1,"TotalConfirmed":10,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":8,"Date":"2020-06-25T19:46:08Z"},{"Country":"Paraguay","CountryCode":"PY","Slug":"paraguay","NewConfirmed":106,"TotalConfirmed":1528,"NewDeaths":0,"TotalDeaths":13,"NewRecovered":0,"TotalRecovered":926,"Date":"2020-06-25T19:46:08Z"},{"Country":"Peru","CountryCode":"PE","Slug":"peru","NewConfirmed":3879,"TotalConfirmed":264689,"NewDeaths":0,"TotalDeaths":8404,"NewRecovered":0,"TotalRecovered":148437,"Date":"2020-06-25T19:46:08Z"},{"Country":"Philippines","CountryCode":"PH","Slug":"philippines","NewConfirmed":1244,"TotalConfirmed":33069,"NewDeaths":0,"TotalDeaths":1186,"NewRecovered":0,"TotalRecovered":8442,"Date":"2020-06-25T19:46:08Z"},{"Country":"Poland","CountryCode":"PL","Slug":"poland","NewConfirmed":592,"TotalConfirmed":33119,"NewDeaths":0,"TotalDeaths":1375,"NewRecovered":0,"TotalRecovered":17573,"Date":"2020-06-25T19:46:08Z"},{"Country":"Portugal","CountryCode":"PT","Slug":"portugal","NewConfirmed":367,"TotalConfirmed":40104,"NewDeaths":0,"TotalDeaths":1540,"NewRecovered":0,"TotalRecovered":25829,"Date":"2020-06-25T19:46:08Z"},{"Country":"Qatar","CountryCode":"QA","Slug":"qatar","NewConfirmed":1199,"TotalConfirmed":90778,"NewDeaths":0,"TotalDeaths":99,"NewRecovered":0,"TotalRecovered":71501,"Date":"2020-06-25T19:46:08Z"},{"Country":"Republic of Kosovo","CountryCode":"XK","Slug":"kosovo","NewConfirmed":0,"TotalConfirmed":2169,"NewDeaths":0,"TotalDeaths":37,"NewRecovered":0,"TotalRecovered":1047,"Date":"2020-06-25T19:46:08Z"},{"Country":"Romania","CountryCode":"RO","Slug":"romania","NewConfirmed":781,"TotalConfirmed":25286,"NewDeaths":0,"TotalDeaths":1539,"NewRecovered":0,"TotalRecovered":17187,"Date":"2020-06-25T19:46:08Z"},{"Country":"Russian Federation","CountryCode":"RU","Slug":"russia","NewConfirmed":14270,"TotalConfirmed":613148,"NewDeaths":0,"TotalDeaths":8349,"NewRecovered":0,"TotalRecovered":355847,"Date":"2020-06-25T19:46:08Z"},{"Country":"Rwanda","CountryCode":"RW","Slug":"rwanda","NewConfirmed":32,"TotalConfirmed":830,"NewDeaths":0,"TotalDeaths":2,"NewRecovered":0,"TotalRecovered":371,"Date":"2020-06-25T19:46:08Z"},{"Country":"Saint Kitts and Nevis","CountryCode":"KN","Slug":"saint-kitts-and-nevis","NewConfirmed":0,"TotalConfirmed":15,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":15,"Date":"2020-06-25T19:46:08Z"},{"Country":"Saint Lucia","CountryCode":"LC","Slug":"saint-lucia","NewConfirmed":0,"TotalConfirmed":19,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":18,"Date":"2020-06-25T19:46:08Z"},{"Country":"Saint Vincent and Grenadines","CountryCode":"VC","Slug":"saint-vincent-and-the-grenadines","NewConfirmed":0,"TotalConfirmed":29,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":27,"Date":"2020-06-25T19:46:08Z"},{"Country":"San Marino","CountryCode":"SM","Slug":"san-marino","NewConfirmed":0,"TotalConfirmed":698,"NewDeaths":0,"TotalDeaths":42,"NewRecovered":0,"TotalRecovered":632,"Date":"2020-06-25T19:46:08Z"},{"Country":"Sao Tome and Principe","CountryCode":"ST","Slug":"sao-tome-and-principe","NewConfirmed":3,"TotalConfirmed":710,"NewDeaths":0,"TotalDeaths":12,"NewRecovered":0,"TotalRecovered":208,"Date":"2020-06-25T19:46:08Z"},{"Country":"Saudi Arabia","CountryCode":"SA","Slug":"saudi-arabia","NewConfirmed":3123,"TotalConfirmed":167267,"NewDeaths":0,"TotalDeaths":1346,"NewRecovered":0,"TotalRecovered":109885,"Date":"2020-06-25T19:46:08Z"},{"Country":"Senegal","CountryCode":"SN","Slug":"senegal","NewConfirmed":199,"TotalConfirmed":6233,"NewDeaths":0,"TotalDeaths":89,"NewRecovered":0,"TotalRecovered":4046,"Date":"2020-06-25T19:46:08Z"},{"Country":"Serbia","CountryCode":"RS","Slug":"serbia","NewConfirmed":143,"TotalConfirmed":13235,"NewDeaths":0,"TotalDeaths":263,"NewRecovered":0,"TotalRecovered":12054,"Date":"2020-06-25T19:46:08Z"},{"Country":"Seychelles","CountryCode":"SC","Slug":"seychelles","NewConfirmed":0,"TotalConfirmed":11,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":11,"Date":"2020-06-25T19:46:08Z"},{"Country":"Sierra Leone","CountryCode":"SL","Slug":"sierra-leone","NewConfirmed":7,"TotalConfirmed":1354,"NewDeaths":0,"TotalDeaths":55,"NewRecovered":0,"TotalRecovered":853,"Date":"2020-06-25T19:46:08Z"},{"Country":"Singapore","CountryCode":"SG","Slug":"singapore","NewConfirmed":304,"TotalConfirmed":42736,"NewDeaths":0,"TotalDeaths":26,"NewRecovered":0,"TotalRecovered":35995,"Date":"2020-06-25T19:46:08Z"},{"Country":"Slovakia","CountryCode":"SK","Slug":"slovakia","NewConfirmed":41,"TotalConfirmed":1630,"NewDeaths":0,"TotalDeaths":28,"NewRecovered":0,"TotalRecovered":1448,"Date":"2020-06-25T19:46:08Z"},{"Country":"Slovenia","CountryCode":"SI","Slug":"slovenia","NewConfirmed":13,"TotalConfirmed":1547,"NewDeaths":0,"TotalDeaths":109,"NewRecovered":0,"TotalRecovered":1376,"Date":"2020-06-25T19:46:08Z"},{"Country":"Somalia","CountryCode":"SO","Slug":"somalia","NewConfirmed":23,"TotalConfirmed":2835,"NewDeaths":0,"TotalDeaths":90,"NewRecovered":0,"TotalRecovered":818,"Date":"2020-06-25T19:46:08Z"},{"Country":"South Africa","CountryCode":"ZA","Slug":"south-africa","NewConfirmed":5688,"TotalConfirmed":111796,"NewDeaths":0,"TotalDeaths":2102,"NewRecovered":0,"TotalRecovered":55045,"Date":"2020-06-25T19:46:08Z"},{"Country":"South Sudan","CountryCode":"SS","Slug":"south-sudan","NewConfirmed":12,"TotalConfirmed":1942,"NewDeaths":0,"TotalDeaths":36,"NewRecovered":0,"TotalRecovered":217,"Date":"2020-06-25T19:46:08Z"},{"Country":"Spain","CountryCode":"ES","Slug":"spain","NewConfirmed":334,"TotalConfirmed":247086,"NewDeaths":0,"TotalDeaths":28325,"NewRecovered":0,"TotalRecovered":150376,"Date":"2020-06-25T19:46:08Z"},{"Country":"Sri Lanka","CountryCode":"LK","Slug":"sri-lanka","NewConfirmed":16,"TotalConfirmed":2007,"NewDeaths":0,"TotalDeaths":11,"NewRecovered":0,"TotalRecovered":1548,"Date":"2020-06-25T19:46:08Z"},{"Country":"Sudan","CountryCode":"SD","Slug":"sudan","NewConfirmed":0,"TotalConfirmed":8889,"NewDeaths":0,"TotalDeaths":548,"NewRecovered":0,"TotalRecovered":3699,"Date":"2020-06-25T19:46:08Z"},{"Country":"Suriname","CountryCode":"SR","Slug":"suriname","NewConfirmed":38,"TotalConfirmed":357,"NewDeaths":0,"TotalDeaths":9,"NewRecovered":0,"TotalRecovered":132,"Date":"2020-06-25T19:46:08Z"},{"Country":"Swaziland","CountryCode":"SZ","Slug":"swaziland","NewConfirmed":16,"TotalConfirmed":690,"NewDeaths":0,"TotalDeaths":7,"NewRecovered":0,"TotalRecovered":319,"Date":"2020-06-25T19:46:08Z"},{"Country":"Sweden","CountryCode":"SE","Slug":"sweden","NewConfirmed":1487,"TotalConfirmed":62324,"NewDeaths":0,"TotalDeaths":5161,"NewRecovered":0,"TotalRecovered":0,"Date":"2020-06-25T19:46:08Z"},{"Country":"Switzerland","CountryCode":"CH","Slug":"switzerland","NewConfirmed":96,"TotalConfirmed":31428,"NewDeaths":0,"TotalDeaths":1956,"NewRecovered":0,"TotalRecovered":29000,"Date":"2020-06-25T19:46:08Z"},{"Country":"Syrian Arab Republic (Syria)","CountryCode":"SY","Slug":"syria","NewConfirmed":11,"TotalConfirmed":242,"NewDeaths":0,"TotalDeaths":7,"NewRecovered":0,"TotalRecovered":94,"Date":"2020-06-25T19:46:08Z"},{"Country":"Taiwan, Republic of China","CountryCode":"TW","Slug":"taiwan","NewConfirmed":0,"TotalConfirmed":446,"NewDeaths":0,"TotalDeaths":7,"NewRecovered":0,"TotalRecovered":435,"Date":"2020-06-25T19:46:08Z"},{"Country":"Tajikistan","CountryCode":"TJ","Slug":"tajikistan","NewConfirmed":63,"TotalConfirmed":5630,"NewDeaths":0,"TotalDeaths":52,"NewRecovered":0,"TotalRecovered":4109,"Date":"2020-06-25T19:46:08Z"},{"Country":"Tanzania, United Republic of","CountryCode":"TZ","Slug":"tanzania","NewConfirmed":0,"TotalConfirmed":509,"NewDeaths":0,"TotalDeaths":21,"NewRecovered":0,"TotalRecovered":183,"Date":"2020-06-25T19:46:08Z"},{"Country":"Thailand","CountryCode":"TH","Slug":"thailand","NewConfirmed":2,"TotalConfirmed":3158,"NewDeaths":0,"TotalDeaths":58,"NewRecovered":0,"TotalRecovered":3023,"Date":"2020-06-25T19:46:08Z"},{"Country":"Timor-Leste","CountryCode":"TL","Slug":"timor-leste","NewConfirmed":0,"TotalConfirmed":24,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":24,"Date":"2020-06-25T19:46:08Z"},{"Country":"Togo","CountryCode":"TG","Slug":"togo","NewConfirmed":7,"TotalConfirmed":583,"NewDeaths":0,"TotalDeaths":13,"NewRecovered":0,"TotalRecovered":384,"Date":"2020-06-25T19:46:08Z"},{"Country":"Trinidad and Tobago","CountryCode":"TT","Slug":"trinidad-and-tobago","NewConfirmed":0,"TotalConfirmed":123,"NewDeaths":0,"TotalDeaths":8,"NewRecovered":0,"TotalRecovered":109,"Date":"2020-06-25T19:46:08Z"},{"Country":"Tunisia","CountryCode":"TN","Slug":"tunisia","NewConfirmed":1,"TotalConfirmed":1160,"NewDeaths":0,"TotalDeaths":50,"NewRecovered":0,"TotalRecovered":1023,"Date":"2020-06-25T19:46:08Z"},{"Country":"Turkey","CountryCode":"TR","Slug":"turkey","NewConfirmed":1492,"TotalConfirmed":191657,"NewDeaths":0,"TotalDeaths":5001,"NewRecovered":0,"TotalRecovered":162848,"Date":"2020-06-25T19:46:08Z"},{"Country":"Uganda","CountryCode":"UG","Slug":"uganda","NewConfirmed":8,"TotalConfirmed":805,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":699,"Date":"2020-06-25T19:46:08Z"},{"Country":"Ukraine","CountryCode":"UA","Slug":"ukraine","NewConfirmed":1953,"TotalConfirmed":40854,"NewDeaths":0,"TotalDeaths":1045,"NewRecovered":0,"TotalRecovered":17538,"Date":"2020-06-25T19:46:08Z"},{"Country":"United Arab Emirates","CountryCode":"AE","Slug":"united-arab-emirates","NewConfirmed":880,"TotalConfirmed":46563,"NewDeaths":0,"TotalDeaths":305,"NewRecovered":0,"TotalRecovered":33703,"Date":"2020-06-25T19:46:08Z"},{"Country":"United Kingdom","CountryCode":"GB","Slug":"united-kingdom","NewConfirmed":655,"TotalConfirmed":308337,"NewDeaths":0,"TotalDeaths":43011,"NewRecovered":0,"TotalRecovered":1330,"Date":"2020-06-25T19:46:08Z"},{"Country":"United States of America","CountryCode":"US","Slug":"united-states","NewConfirmed":34347,"TotalConfirmed":2381369,"NewDeaths":0,"TotalDeaths":121228,"NewRecovered":0,"TotalRecovered":647548,"Date":"2020-06-25T19:46:08Z"},{"Country":"Uruguay","CountryCode":"UY","Slug":"uruguay","NewConfirmed":17,"TotalConfirmed":902,"NewDeaths":0,"TotalDeaths":25,"NewRecovered":0,"TotalRecovered":815,"Date":"2020-06-25T19:46:08Z"},{"Country":"Uzbekistan","CountryCode":"UZ","Slug":"uzbekistan","NewConfirmed":328,"TotalConfirmed":6990,"NewDeaths":0,"TotalDeaths":19,"NewRecovered":0,"TotalRecovered":4560,"Date":"2020-06-25T19:46:08Z"},{"Country":"Venezuela (Bolivarian Republic)","CountryCode":"VE","Slug":"venezuela","NewConfirmed":179,"TotalConfirmed":4366,"NewDeaths":0,"TotalDeaths":35,"NewRecovered":0,"TotalRecovered":1327,"Date":"2020-06-25T19:46:08Z"},{"Country":"Viet Nam","CountryCode":"VN","Slug":"vietnam","NewConfirmed":3,"TotalConfirmed":352,"NewDeaths":0,"TotalDeaths":0,"NewRecovered":0,"TotalRecovered":329,"Date":"2020-06-25T19:46:08Z"},{"Country":"Western Sahara","CountryCode":"EH","Slug":"western-sahara","NewConfirmed":0,"TotalConfirmed":10,"NewDeaths":0,"TotalDeaths":1,"NewRecovered":0,"TotalRecovered":8,"Date":"2020-06-25T19:46:08Z"},{"Country":"Yemen","CountryCode":"YE","Slug":"yemen","NewConfirmed":23,"TotalConfirmed":1015,"NewDeaths":0,"TotalDeaths":261,"NewRecovered":0,"TotalRecovered":356,"Date":"2020-06-25T19:46:08Z"},{"Country":"Zambia","CountryCode":"ZM","Slug":"zambia","NewConfirmed":20,"TotalConfirmed":1497,"NewDeaths":0,"TotalDeaths":18,"NewRecovered":0,"TotalRecovered":1213,"Date":"2020-06-25T19:46:08Z"},{"Country":"Zimbabwe","CountryCode":"ZW","Slug":"zimbabwe","NewConfirmed":5,"TotalConfirmed":530,"NewDeaths":0,"TotalDeaths":6,"NewRecovered":0,"TotalRecovered":64,"Date":"2020-06-25T19:46:08Z"}],"Date":"2020-06-25T19:46:08Z"}"

    private var messageApiStatus: MutableLiveData<Boolean>? = null
    private val covidData = MutableLiveData<CovidResponse>()


    val allCovidData: MutableLiveData<CovidResponse>
        get() {
            fetchCovidData()
            return covidData
        }


    fun fetchCovidData() {

        val call1 = RetrofitClient.instance.api.covidData
        call1.enqueue(object : Callback<CovidResponse> {

            override fun onResponse(call: Call<CovidResponse>, response: Response<CovidResponse>) {
                if (response.code() == 200) {
                    val reponse = response.body()
                    if (reponse !=  null) {
                        covidData.postValue(reponse)
                    } else {
                        messageApiStatus!!.postValue(false)
                    }
                } else {
                    messageApiStatus!!.postValue(false)
                }
            }

            override fun onFailure(call: Call<CovidResponse>, t: Throwable) {
                messageApiStatus!!.value = false
            }
        })
    }

}