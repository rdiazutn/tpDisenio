<template>
  <TheLayoutWithHeader title="transacciones.presupuestos" :loading="loading">
    <template #filter>
      <v-row no-gutters>
        <v-col class="text-right">
          <ThePrimaryButton
            class="mt-0"
            :inner-text="$t('search')"
            icon="mdi-magnify"
            @click="getPresupuestos()"
          />
          <PresupuestoForm
            @created="getPresupuestos()"
          />
        </v-col>
      </v-row>
    </template>
    <template #body>
      <TheFilterTable
        :page-info.sync="pageInfo"
        :items="presupuestos"
        :headers="headers"
        :total="totalList"
        @change="getPresupuestos()"
      />
    </template>
  </TheLayoutWithHeader>
</template>
<script>
import TheLayoutWithHeader from '~/components/General/Layouts/TheLayoutWithHeader'
import TheFilterTable from '~/components/General/Tables/TheFilterTable'
import ThePrimaryButton from '~/components/General/Buttons/ThePrimaryButton'
import PresupuestoForm from '~/components/Presupuestos/PresupuestoForm'
export default {
  components: {
    TheLayoutWithHeader,
    TheFilterTable,
    ThePrimaryButton,
    PresupuestoForm
  },
  data: () => ({
    pageInfo: {
      page: 1,
      itemsPerPage: 20
    },
    totalList: 5,
    presupuestos: [],
    loading: false
  }),
  computed: {
    headers () {
      return [
        {
          text: this.$t('presupuestos.id'),
          align: 'center',
          sortable: false,
          value: 'operacionId'
        },
        {
          text: this.$t('presupuestos.egreso_id'),
          align: 'center',
          sortable: false,
          value: 'egresoID'
        },
        {
          text: this.$t('presupuestos.entidad_realizadora'),
          align: 'start',
          sortable: false,
          value: 'entidadRealizadora.nombre'
        },
        {
          text: this.$t('presupuestos.documento_comercial'),
          align: 'center',
          sortable: false,
          value: 'documentoComercial.numero'
        },
        {
          text: this.$t('presupuestos.total'),
          align: 'end',
          sortable: false,
          value: 'total'
        }
      ]
    }
  },
  methods: {
    getPresupuestos () {
      this.loading = true
      this.$presupuestoService.getPresupuestos(this.pageInfo).then((result) => {
        if (result) {
          this.totalList = result.total
          this.presupuestos = result.data
        }
      }).finally(this.stopLoading)
    },
    stopLoading () {
      this.loading = false
    },
    createPresupuesto (item) {
      return () => 1
    },
    updatePresupuesto (item) {
      return () => 1
    }
  }
}
</script>
